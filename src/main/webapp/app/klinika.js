Vue.component("klinika", {
	data: function () {
	    return {
	    	input: {	    		 
                naziv: "",
                opis: "",
                adresa: ""
            		},
	    	klinike:[],
	    	id:null,
	    	izmena:""
	    }
	},
	template: ` 
<div class="oneoption">
<div>
	<div class="jumbotron">
	  <h2>Klinike</h2>
	  <p>Pretraga i dodavanje</p> 
	</div>
   <table align="left" class="table">
		<tr>
		   <th>ID</th>
		   <th>Naziv</th>
		   <th>Adresa</th>
		   <th>Opis</th>
		</tr>
		<tr v-for="k in klinike" >
			<td class="myclass">{{k.id}}</td>
			<td class="myclass">{{k.naziv}}</td>
			<td class="myclass">{{k.adresa}}</td>
			<td class="myclass">{{k.opis}}</td>
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
        	.post('rest/klinika/dodaj', {"id": null, "naziv":this.input.naziv, "adresa":this.input.adresa, "naziv":this.input.opis})
			.then(response => this.$router.replace({ name: "administratorKlinike" }));
        }
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/AdminK')
	    .then((response) => {
	    	console.log(response.data);	
	    })
	    .catch(response => {
			this.$router.push("/");
		});
		axios
	    .get('rest/klinika')
	    .then(response => (this.klinike=response.data));
	},	
});