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
<div class="godisnjidiv">
    <form>
		<h2 class="text-center">Slanje zahteva za godisnji odmor/odsustvo</h2>       
		<div class="form-group">
			<div class = "celija odcelija">
				<label for="ime">Od: </label>
   				<input type="date" id="od" class="form-control" v-model="pocetak"  required>
   			</div>
   			<div class = "celija docelija">
   				<label for="prezime">Do: </label>
    			<input type="date" id = "do" class="form-control" v-model="kraj" placeholder="Datum" required>
    		</div>
    	</div>
		<div class="form-group">
    		<button type="button" class="btn btn-primary btn-block zahtevbtn" v-on:click="posalji()">Posalji zahtev</button>
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
	    	var od = document.getElementById('od').value; 
    		var dos = document.getElementById('do').value; 
    		var odDate = new Date(od); 
    		var doDate = new Date(dos); 
            var currDate = new Date();
            if(odDate<=currDate || doDate<=currDate || odDate>=doDate){
            	return false;
            }
	    	return true;	
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
		document.getElementById('od').valueAsDate = new Date();
		document.getElementById('do').valueAsDate = new Date();
		}
	
});