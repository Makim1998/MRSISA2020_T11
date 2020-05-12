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
	    	izmena: {
	    		id: null,
	    		sifra: ""
	    	}
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
			<td><input type="text" class="fotrol" v-model="input.sifra" placeholder="Sifra"></td>
			<td><input type="text" class="fotrol" v-model="input.stavkaId" placeholder="StavkaId"></td>
			<td><select v-model="input.tip">
				<option>LEK</option>
				<option>DIJAGNOZA</option>
			</select></td>
			<td><input class="btn btn-success" type='button' value='Dodavanje'  v-on:click="dodaj()"/></td>
		</tr>
		<tr>
			<td><select v-for="s in stavke" v-model="izmena.id">
				<option>{{s.id}}</option>
			</select></td>
			<td>Nova sifra: </td>
			<td><input type="text" class="fotrol" v-model="izmena.sifra" placeholder="StavkaId"></td>
			<td><input class="btn btn-success" type='button' value='Izmena' v-on:click="izmeni()"/></td>
		</tr>
   </table>
</div>
</div>		  
`
	, 
	methods : {
		dodaj() {
        	axios
        	.post('rest/sifarnik/dodaj', {"id": null, "sifra":this.input.sifra, "stavkaId":this.input.stavkaId, "tip":this.input.tip})
			.then(response => this.$router.replace({ name: "administratorCentra" }));
        },
        izmeni(){
        	axios
        	.put('rest/sifarnik/izmeni', {"id": this.izmena.id, "novaSifra": this.izmena.sifra})
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