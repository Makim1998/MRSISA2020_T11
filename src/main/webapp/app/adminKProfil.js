Vue.component('adminKProfil',{
	data:function(){
		return{
			id:null,
			username: "",
            ime: "",
            prezime: "",
            adresa: "",
            grad: "",
            drzava: "",
            lozinka: "",
            ponovljena: "",
            brojOsiguranika: "",
			kc_id:null
		}
	},
	template: ` 
<div class="oneoption">
<div id = "login" class="sidenavlogin">
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
</div>	  		  
`
	, 
	methods : {
		izmeni() {
			if(this.proveraPolja()){
				axios
			    .put('rest/adminK/izmeni',{
			    	"id":this.id,
			        "ime": this.ime,
			        "prezime": this.prezime,
			        "username":this.username,
			        "password": this.lozinka,
			        "adresa": this.adresa,
			        "grad": this.grad,
			        "drzava": this.drzava,
			        "kc_id":this.kc_id
			    })
			    .then((response) => {
			    	alert("Podaci su izmenjeni");
			    	this.id=response.data.id;
			    	this.username = response.data.username;
			    	this.ime = response.data.ime;
			    	this.lozinka = response.data.password;
			    	this.ponovljena = response.data.password;
			    	this.prezime = response.data.prezime;
			    	this.adresa = response.data.adresa;
			    	this.grad = response.data.grad;
			    	this.drzava = response.data.drzava;		    	
			    	this.kc_id=response.data.kc_id;
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
	    .get('rest/login/getConcreteUser/AdminK')
	    .then((response) => {
	    	this.id=response.data.id;
	    	this.username = response.data.email;
	    	this.ime = response.data.ime;
	    	this.lozinka = response.data.password;
	    	this.ponovljena = response.data.password;
	    	this.prezime = response.data.prezime;
	    	this.adresa = response.data.adresa;
	    	this.grad = response.data.grad;
	    	this.drzava = response.data.drzava;		    	
	    	this.brojOsiguranika = response.data.brojOsiguranika;
	    	this.kc_id=response.data.kc_id;
		    })
		    .catch(response => {
				this.$router.push("/");
			});
		}
});