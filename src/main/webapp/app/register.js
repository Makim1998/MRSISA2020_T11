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
<div id = "register">
    <form>
		<h2 class="text-center">Registracija</h2>       
		<div class="form-group">
			<label for="username">Email: </label>
   			<input type="text" id = "username" class="form-control" v-model="input.username"  >
		</div>
		<div class="form-group" id = "vrsta">
			<div class = "celija">
				<label for="ime">Ime: </label>
   				<input type="text" id = "ime" class="form-control" v-model="input.ime" placeholder="Ime">
   			</div>
   			<div class = "celija">
   				<label for="prezime">Prezime: </label>
    			<input type="text" id = "prezime" class="form-control" v-model="input.prezime" placeholder="Prezime">
    		</div>
    	</div>
    	<div class="form-group"  id = "vrsta">
    		<div class = "celija">
				<label for="adresa">Adresa: </label>
   				<input type="text" id = "adresa" class="form-control" v-model="input.adresa" placeholder="Adresa">
   			</div>
   			<div class = "celija">
   				<label for="grad">Grad: </label>
    			<input type="text" id = "grad" class="form-control" v-model="input.grad" placeholder="Grad">
    		</div>
		</div>
		<div class="form-group">
			<label for="drzava">Drzava: </label>
    		<input type="text"  id = "drzava" class="form-control" v-model="input.drzava" placeholder="Drzava">
		</div>
		<div class="form-group">
			<label for="broj">Jeinstveni br. osiguranika: </label>
    		<input type="text" id = "broj" class="form-control"  v-model="input.brojOsiguranika" placeholder="Jedinstveni broj osiguranika">
		</div>
		<div class="form-group" id = "vrsta">
			<div class = "celija">
				<label for="lozinka">Lozinka: </label>
    			<input type="password" id="lozinka" class="form-control" v-model="input.lozinka" placeholder="Lozinka">
   			</div>
   			<div class = "celija">
   				<label for="ponovljena">Ponovite lozinku: </label>
    			<input type="password" id = "ponovljena" class="form-control" v-model="input.ponovljena" placeholder="Ponovi lozinku">
    		</div>
		</div>
		<div class="form-group">
    		<button type="button" class="btn btn-primary btn-block" v-on:click="register()">Registruj se</button>
		</div>
		<div class="form-group">
    		<router-link :to="{ name: 'login'}" >Prijava</router-link>
		</div>
    </form>
</div>		    
`
	, 
	methods : {
		register() {
	    	console.log("registracija");
	    	if(this.proveraPolja()){
	    		axios
			    .post('rest/login/register',{
			    	"email": this.input.username,
			    	"ime": this.input.ime,
		            "prezime": this.input.prezime,
		            "adresa":this.input.adresa,
		            "grad": this.input.grad,
		            "drzava": this.input.drzava,
		            "password": this.input.lozinka,
		            "brojOsiguranika":  this.input.brojOsiguranika
				})
			    .then((response) => {
			    	alert("Uspesno ste se registrovali!");
			    	console.log(response.data);
			    	
			    })
			    .catch(function(error){
	    				if(error.response){
	    					alert("Doslo je do greske");
	    				};
	    		});
	    	}
	    	else{
	    		alert("Popunite ispravno sva polja");
	    	}
			
        },
        proveraPolja(){
        	if(!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(this.input.username)){
        		alert("Email adresa nije validna")
        		return false;
        	}
        	if(this.input.lozinka !== this.input.ponovljena){
        		alert("Niste dobro ponovili lozinku")
        		return false;
        	}
        	if(this.input.ime == ""){
        		return false;
        	}
        	if(this.input.prezime == ""){
        		return false;
        	}
        	if(this.input.adresa == ""){
        		return false;
        	}
        	if(this.input.grad == ""){
        		return false;
        	}
        	if(this.input.drzava == ""){
        		return false;
        	}
        	if(this.input.lozinka == ""){
        		return false;
        	}
        	return true;
        	
        	
        }
    }
     
});