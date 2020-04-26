Vue.component('karton',{
	data:function(){
		return{
			email: "",
			ime: "",
            prezime: "",
            pol: "",
            datumRodj: "",
            krvna: ""
		}
	},
	template: ` 
<div id = "karton">
    <form>
		<h2 class="text-center">Zdravstveni karton</h2>       
		<div class="form-group">
			<label for="ime">Ime: </label>
   			<input type="text" id = "username" class="form-control" v-model="ime"  disabled>
		</div>
		<div class="form-group">
			<label for="prezime">Prezime: </label>
			<input type="text" id = "prezime" class="form-control" v-model="prezime" disabled>
    	</div>
    	<div class="form-group">
			<label for="pol">Pol: </label>
   			<input type="text" id = "pol" class="form-control" v-model="pol" disabled>
		</div>
		<div class="form-group">
			<label for="rodj">Datum rodjenja: </label>
    		<input type="text" id = "rodj" class="form-control"  v-model="datumRodj" disabled>
		</div>
		<div class="form-group">
			<label for="grupa">Krvna grupa: </label>
    		<input type="text"  id = "grupa" class="form-control" v-model="krvna" disabled>
		</div>
    </form>
</div>		  		  
`
	, 
	mounted(){
		axios
	    .get('rest/login/getConcreteUser')
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
		    });
	    });
		
		
	}
});