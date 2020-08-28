Vue.component('karton',{
	data:function(){
		return{
			email: "",
			ime: "",
            prezime: "",
            pol: "",
            datumRodj: "",
            krvna: "",
            istorija: "",
            visina: "",
            tezina: "",
            alergije: "",
            propisano: ""
		}
	},
	template: ` 
<div id = "karton" class="sidenavlogin">
    <form>
		<h2 class="text-center">Zdravstveni karton</h2>       
    	<div class="form-group">
			<label for="pol">Pol: </label>
   			<input type="text" id = "pol" class="form-control" v-model="pol" disabled>
		</div>
		<div class="form-group">
			<label for="rodj">Datum rodjenja: </label>
    		<input type="text" id = "rodj" class="form-control"  v-model="datumRodj" disabled>
		</div>
		<div class="form-group">
			<label for="visina">Visina: </label>
    		<input type="text"  id = "visina" class="form-control" v-model="visina" disabled>
		</div>
		<div class="form-group">
			<label for="tezina">Tezina: </label>
    		<input type="text"  id = "tezina" class="form-control" v-model="tezina" disabled>
		</div>
		<div class="form-group">
			<label for="krvna">Krvna grupa: </label>
    		<input type="text"  id = "krvna" class="form-control" v-model="krvna" disabled>
		</div>
		<div class="form-group">
			<label for="alergije">Alergije: </label>
    		<input type="text"  id = "alergije" class="form-control" v-model="alergije" disabled>
		</div>
		<div class="form-group">
			<label for="propisano">Propisani lek(ovi): </label>
    		<input type="text"  id = "propisano" class="form-control" v-model="propisano" disabled>
		</div>
		<div class="form-group">
			<label for="istorija">Istorija bolesti: </label>
    		<textarea style="height:100px;" id="istorija" class="form-control" v-model="istorija" disabled></textarea>
		</div>
    </form>
</div>		  		  
`
	, 
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/Pacijent')
	    .then((response) => {
	    	console.log(response.data);
	    	this.email = response.data.email;
	    	console.log(this.email);
	    	axios
		    .get('rest/pacijent/getKarton?email='+ this.email)
		    .then((response) => {
		    	console.log(response.data);
		    	this.ime = response.data.ime;
		    	console.log(this.ime);
		    	this.prezime = response.data.prezime;
		    	this.krvna = response.data.krvnaGrupa;
		    	this.datumRodj = response.data.datumRodjenja;
		    	console.log(this.datumRodj);
		    	this.pol = response.data.pol;
		    	this.istorija = response.data.istorijaBolesti;
		    	this.visina = response.data.visina;
		    	this.tezina = response.data.tezina;
		    	this.alergije = response.data.alergije;
		    	this.propisano = response.data.propisano;
		    })
	    	 .catch(function(error){
 				if(error.response){
 					alert("Jos uvek nije kreiran karton za pacijenta.");
 				};
	    	 });	
	    })
	    .catch(response => {
			this.$router.push("/");
		});
		
		
	}
});