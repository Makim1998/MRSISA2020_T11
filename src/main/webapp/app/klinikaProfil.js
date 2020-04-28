Vue.component("klinikaProfil", {
	data:function(){
		return{
			id:null,
            naziv: "",
            adresa: "",
            opis: "",
            kc_id:null
		}
	},
	template: ` 
<div class="oneoption">
<div id = "login" class="sidenavlogin">
    <form>
		<h2 class="text-center">Pregled i izmena profila klinike</h2>       
		<div class="form-group">
			<label for="username">ID Klinike: </label>
   			<input type="text" id = "username" class="form-control" v-model="id"  disabled>
		</div>
		<div class="form-group">
			<label for="ime">Naziv: </label>
   			<input type="text" id = "ime" class="form-control" v-model="naziv" placeholder="Naziv">
    	</div>
    	<div class="form-group">
    		<label for="adresa">Adresa: </label>
   			<input type="text" id = "adresa" class="form-control" v-model="adresa" placeholder="Adresa">
		</div>
		<div class="form-group">
    		<label for="opis">Opis: </label>
   			<input type="text" id = "adresa" class="form-control" v-model="opis" placeholder="Opis">
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
			axios
		    .put('rest/adminK/izmeni',{
		    	"id":this.id,
		        "naziv": this.naziv,
		        "adresa": this.adresa,
		        "opis":this.opis,
		        "kc_id":this.kc_id,
		    })
		    .then((response) => {
		    	alert("Podaci su izmenjeni");
		    	this.id=response.data.id;
		    	this.naziv=response.data.naziv;
		    	this.adresa=response.data.adresa;
		    	this.opis=response.data.opis;
		    	this.kc_id=response.klinicki_centar_id;
		    });
        },
	},
	mounted(){
		axios
	    .get('rest/login/getKlinika')
	    .then((response) => {
	    	this.id=response.data.id;
	    	this.naziv=response.data.naziv;
	    	this.adresa=response.data.adresa;
	    	this.opis=response.data.opis;
	    	this.kc_id=response.klinicki_centar_id;
		    })
		    .catch(response => {
				this.$router.push("/");
			});
		}
});