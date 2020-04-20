Vue.component("register", {
	data: function () {
		    return {
		    	 input: {
	                    username: "",
	                    password: "",
	                    ime: "",
	                    prezime: "",
	                    adresa: "",
	                    grad: "",
	                    drzava: "",
	                    lozinka: "",
	                    ponovljena: "",
	                    brojOsiguranika: ""
	                }
		    }
	},
	template: `
<div id = "login">
    <form>
		<h2 class="text-center">Register</h2>       
		<div class="form-group">
   			<input type="text" class="form-control" v-model="input.username" placeholder="Username">
		</div>
		<div class="form-group" id = "vrsta">
			<div class = "celija">
   				<input type="text" class="form-control" v-model="input.ime" placeholder="Ime">
   			</div>
   			<div class = "celija">
    			<input type="text" class="form-control" v-model="input.prezime" placeholder="Prezime">
    		</div>
    	</div>
    	<div class="form-group">
   			<input type="text" class="form-control" v-model="input.adresa" placeholder="Adresa">
		</div>
		<div class="form-group">
    		<input type="text" class="form-control" v-model="input.grad" placeholder="Grad">
		</div>
		<div class="form-group">
    		<input type="text" class="form-control" v-model="input.drzava" placeholder="Drzava">
		</div>
		<div class="form-group">
    		<input type="text" class="form-control" v-model="input.brojOsiguranika" placeholder="Jedinstveni broj osiguranika">
		</div>
		<div class="form-group">
    		<input type="password" class="form-control" v-model="input.password" placeholder="Lozinka">
		</div>
		<div class="form-group">
    		<input type="password" class="form-control" v-model="input.ponovljena" placeholder="Ponovi lozinku">
		</div>
		<div class="form-group">
    		<button type="button" class="btn btn-primary btn-block" v-on:click="register()">Registruj se</button>
		</div>
		<div class="form-group">
    		<router-link :to="{ name: 'login' }" >Login</router-link>
		</div>
    </form>
</div>		  
`
	, 
	methods : {
		register() {
            if(this.input.username != "" && this.input.password != "") {
                /*if(this.input.username == "pacijent" && this.input.password == "pacijent") {
                    this.$emit("authenticated", true);
                    this.$router.replace({ name: "homepage" });
                } else {
                    console.log("The username and / or password is incorrect");
                }*/
            	axios
    			.post('rest/register', {"username": this.input.username, "password":this.input.password})
    			.then(response => this.$router.replace({ name: "homepage" }))
    			.catch(function(error){
    				if(error.response){
    					alert("Pogresni kor. ime i lozinka");
    				};
    			});
            } else {
                console.log("A username and password must be present");
            }
        }
	},

});