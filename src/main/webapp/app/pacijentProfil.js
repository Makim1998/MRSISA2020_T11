Vue.component('pacijentProfil',{
	data:function(){
		return{
		
			username: "",
            ime: "",
            prezime: "",
            adresa: "",
            grad: "",
            drzava: "",
            lozinka: "",
            ponovljena: "",
            brojOsiguranika: ""
			
		}
	},
	template: ` 
<div id = "login">
    <form>
		<h2 class="text-center">Pregled i promena profila</h2>       
		<div class="form-group">
			<label for="username">Email: </label>
   			<input type="text" id = "username" class="form-control" v-model="username"  disabled>
		</div>
		<div class="form-group" id = "vrsta">
			<div class = "celija">
				<label for="ime">Ime: </label>
   				<input type="text" id = "ime" class="form-control" v-model="ime" placeholder="Ime">
   			</div>
   			<div class = "celija">
   				<label for="prezime">Prezime: </label>
    			<input type="text" id = "prezime" class="form-control" v-model="prezime" placeholder="Prezime">
    		</div>
    	</div>
    	<div class="form-group">
    		<label for="adresa">Adresa: </label>
   			<input type="text" id = "adresa" class="form-control" v-model="adresa" placeholder="Adresa">
		</div>
		<div class="form-group">
    		<input type="text" id = "grad" class="form-control" v-model="grad" placeholder="Grad">
		</div>
		<div class="form-group">
    		<input type="text"  id = "drzava" class="form-control" v-model="drzava" placeholder="Drzava">
		</div>
		<div class="form-group">
    		<input type="text" id = "drzava" class="form-control" disabled v-model="brojOsiguranika" placeholder="Jedinstveni broj osiguranika">
		</div>
		<div class="form-group">
    		<input type="password" class="form-control" v-model="lozinka" placeholder="Lozinka">
		</div>
		<div class="form-group">
    		<input type="password" class="form-control" v-model="ponovljena" placeholder="Ponovi lozinku">
		</div>
		<div class="form-group">
    		<button type="button" class="btn btn-primary btn-block" v-on:click="izmeni()">Izmeni profil</button>
		</div>
    </form>
</div>		  		  
`
	, 
	methods : {
		izmeni() {
			axios
		    .post('rest/pacijent/profil',{
		        firstName: 'Fred',
		        lastName: 'Flintstone'
		    })
		    .then((response) => {
		    	console.log(response.data);
		    	this.username = response.data.email;
		    	console.log(this.username);
		    	console.log(response.data.email);
		    	this.ime = response.data.ime;
		    	this.lozinka = response.data.password;
		    	this.ponovljena = response.data.password;
		    	console.log(this.ime);
		    	this.prezime = response.data.prezime;
		    	this.adresa = response.data.adresa;
		    	this.grad = response.data.grad;
		    	this.drzava = response.data.drzava;
		    	
		    	this.brojOsiguranika = response.data.brojOsiguranika;
		    	console.log(response.data);
		    	
		    });
        },
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser')
	    .then((response) => {
	    	console.log(response.data);
	    	this.username = response.data.email;
	    	console.log(this.username);
	    	console.log(response.data.email);
	    	this.ime = response.data.ime;
	    	this.lozinka = response.data.password;
	    	this.ponovljena = response.data.password;
	    	console.log(this.ime);
	    	this.prezime = response.data.prezime;
	    	this.adresa = response.data.adresa;
	    	this.grad = response.data.grad;
	    	this.drzava = response.data.drzava;
	    	
	    	this.brojOsiguranika = response.data.brojOsiguranika;
	    	console.log(response.data);
	    	
	    });
	}
});