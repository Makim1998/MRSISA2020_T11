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
<div class="oneoption">
<div id = "login" class="sidenavlogin">
<div id = "register">
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
    	<div class="form-group"  id = "vrsta">
    		<div class = "celija">
				<label for="adresa">Adresa: </label>
   				<input type="text" id = "adresa" class="form-control" v-model="adresa" placeholder="Adresa">
   			</div>
   			<div class = "celija">
   				<label for="grad">Grad: </label>
    			<input type="text" id = "grad" class="form-control" v-model="grad" placeholder="Grad">
    		</div>
		</div>
		<div class="form-group">
			<label for="drzava">Drzava: </label>
    		<input type="text"  id = "drzava" class="form-control" v-model="drzava" placeholder="Drzava">
		</div>
		<div class="form-group">
			<label for="broj">Jedinstveni br. osiguranika: </label>
    		<input type="text" id = "broj" class="form-control" disabled v-model="brojOsiguranika" placeholder="Jedinstveni broj osiguranika">
		</div>
		<div class="form-group" id = "vrsta">
			<div class = "celija">
				<label for="lozinka">Lozinka: </label>
    			<input type="password" id="lozinka" class="form-control" v-model="lozinka" placeholder="Lozinka">
   			</div>
   			<div class = "celija">
   				<label for="ponovljena">Ponovite lozinku: </label>
    			<input type="password" id = "ponovljena" class="form-control" v-model="ponovljena" placeholder="Ponovi lozinku">
    		</div>
		</div>
		<div class="form-group">
    		<button type="button" class="btn btn-primary btn-block" v-on:click="izmeni()">Izmeni profil</button>
		</div>
    </form>
</div>	
</div>	  		  
`
	, 
	methods : {
		izmeni() {
	    	console.log(this.ime);
	    	console.log(this.username);
	    	if(this.proveraPolja()){
				axios
			    .put('rest/pacijent/profil',{
			    	"email": this.username,
			    	"ime": this.ime,
		            "prezime": this.prezime,
		            "adresa":this.adresa,
		            "grad": this.grad,
		            "drzava":this.drzava,
		            "lozinka": this.lozinka
					
				})
			    .then((response) => {
			    	alert("Uspesno ste izmenili profil!");
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
	    	else{
	    		alert("Popunite ispravno sva polja");
	    	}
			
        },
        proveraPolja(){
        	if(this.lozinka !== this.ponovljena){
        		alert("Niste dobro ponovili lozinku")
        		return false;
        	}
        	if(this.ime == ""){
        		return false;
        	}
        	if(this.prezime == ""){
        		return false;
        	}
        	if(this.adresa == ""){
        		return false;
        	}
        	if(this.grad == ""){
        		return false;
        	}
        	if(this.drzava == ""){
        		return false;
        	}
        	if(this.lozinka == ""){
        		return false;
        	}
        	return true;
        	
        	
        }
        
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/Pacijent')
	    .then((response) => {
	    	console.log(response.data);
	    	this.username = response.data.email;
	    	console.log(this.username);
	    	console.log(response.data.email);
	    	this.ime = response.data.ime;
	    	this.lozinka = response.data.password;
	    	this.ponovljena = response.data.password;
	    	console.log(this.lozinka);
	    	this.prezime = response.data.prezime;
	    	this.adresa = response.data.adresa;
	    	this.grad = response.data.grad;
	    	this.drzava = response.data.drzava;
	    	
	    	this.brojOsiguranika = response.data.brojOsiguranika;
	    	console.log(response.data);
		    })
		    .catch(response => {
				this.$router.push("/");
			});
		}
	    	
	    });