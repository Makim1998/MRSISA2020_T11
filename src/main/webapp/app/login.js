Vue.component("login", {
	data: function () {
		    return {
		    	 input: {
	                    username: "",
	                    password: ""
	                }
		    }
	},
	template: `
<div id = "login">
    <form>
		<h2 class="text-center">Log in</h2>       
		<div class="form-group">
   			<input type="text" class="form-control" v-model="input.username" placeholder="Username">
		</div>
		<div class="form-group">
    		<input type="password" class="form-control" v-model="input.password" placeholder="Password">
		</div>
		<div class="form-group">
    		<button type="button" class="btn btn-primary btn-block" v-on:click="login()">Log in</button>
		</div>
    </form>
</div>		  
`
	, 
	methods : {
		login() {
            if(this.input.username != "" && this.input.password != "") {
                /*if(this.input.username == "pacijent" && this.input.password == "pacijent") {
                    this.$emit("authenticated", true);
                    this.$router.replace({ name: "homepage" });
                } else {
                    console.log("The username and / or password is incorrect");
                }*/
            	axios
    			.post('rest/login', {"username": this.input.username, "password":this.input.password})
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