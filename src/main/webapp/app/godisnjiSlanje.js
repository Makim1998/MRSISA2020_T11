Vue.component('godisnjiSlanje',{
	data:function(){
		return{
			id:null,
			username: "",
            pocetak:null,
            kraj:null,
            razlog:"",
			kc_id:null,
		}
	},
	template: ` 
<div class="oneoption">
<div id = "login" class="sidenavlogin">
    <form style="width:150%;">
		<h2 class="text-center">Slanje zahteva za godisnji odmor/odsustvo</h2>       
		<div class="form-group" id = "vrsta">
			<div class = "celija">
				<label for="ime">Od: </label>
   				<input type="date" id = "ime" class="form-control" v-model="pocetak" placeholder="Datum">
   			</div>
   			<div class = "celija">
   				<label for="prezime">Do: </label>
    			<input type="date" id = "prezime" class="form-control" v-model="kraj" placeholder="Datum">
    		</div>
    	</div>
		<div class="form-group">
    		<button type="button" class="btn btn-primary btn-block" v-on:click="posalji()">Posalji zahtev</button>
		</div>
    </form>
</div>	
</div>	  		  
`
	, 
	methods : {
		posalji() {
			if(this.proveraPolja()){
				axios
			    .post('rest/godisnji/posalji',{
			    	"id":null,
			        "datumPocetka": this.pocetak,
			        "datumKraja": this.kraj,
			        "razlog":null,
			        "prihvacenOdbijen":null,
			        "medOsoblje_id":this.id,
			        
			    })
			    .then((response) => {
			    	alert("Zahtev je uspesno poslat.");
			    	this.pocetak=null;
			    	this.kraj =null;
			    	//this.razlog = "";	    	
			    });
	    	}
	    	else{
	    		alert("Popunite ispravno sva polja");
	    	}
	    },
	    proveraPolja(){
	    	return true;
	    	//alert("Niste dobro ponovili lozinku") 	
	    }
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/Lekar')
	    .then((response) => {
	    	this.id=response.data.id;
	    	this.username = response.data.email;
	    	this.kc_id=response.data.kc_id;
		    })
		    .catch(response => {
				this.$router.push("/");
			});
		}
});