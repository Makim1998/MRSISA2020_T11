Vue.component("sifarnik", {
	data: function () {
	    return {
	    	input: {	    		 
                sifra: "",
                stavkaId: null,
                tip: ""
            		},
	    	stavke:[],
	    	id:null,
	    	izmena:""
	    }
	},
	template: ` 
<div class="oneoption">
<div>
	<div class="jumbotron">
	  <h2>Sifarnik za dijagnoze i lekove</h2>
	  <p>Pretraga i dodavanje</p> 
	</div>
   <table align="left" class="table">
		<tr>
		   <th>ID</th>
		   <th>Sifra</th>
		   <th>ID Stavke</th>
		   <th>Tip stavke</th>
		</tr>
		<tr v-for="s in stavke" >
			<td class="myclass">{{s.id}}</td>
			<td class="myclass">{{s.sifra}}</td>
			<td class="myclass">{{s.stavkaId}}</td>
			<td class="myclass">{{s.tip}}</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="text" class="fotrol" v-model="input.naziv" placeholder="Naziv"></td>
			<td><input type="text" class="fotrol" v-model="input.adresa" placeholder="Adresa"></td>
			<td><input type="text" class="fotrol" v-model="input.opis" placeholder="Opis"></td>
			<td><input class="btn btn-success" type='button' value='Dodavanje'  v-on:click="dodaj()"/></td>
		</tr>	
   </table>
   <div class="form-popup" id="myForm">
    <h6>Izmena ID:{{this.id}}</h6>
    <input type="text" class="psw" v-model="izmena" placeholder="Naziv pregleda">
    </br></br>
	<button type="button" class="btn maal leftbutton" v-on:click="izmeni()">Potvrdi</button>
	<button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
   </div>
</div>
</div>		  
`
	, 
	methods : {
		dodaj() {
        	axios
        	.post('rest/sifarnik/dodaj', {"id": null, "sifra":this.input.sifra, "stavkaId":this.input.stavkaId, "tip":this.input.tip})
			.then(response => this.$router.replace({ name: "administratorCentra" }));
        }
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/AdminKC')
	    .then((response) => {
	    	console.log(response.data);	
	    })
	    .catch(response => {
			this.$router.push("/");
		});
		axios
	    .get('rest/sifarnik')
	    .then(response => (this.stavke=response.data));
	},	
});