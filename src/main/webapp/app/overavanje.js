Vue.component('overavanje', {
	data: function () {
	    return {
	    	recepti:[],
	    	id:null
	    }
	},
	template: ` 
<div class="oneoption_AKC">
<div>
	<div class="jumbotron">
	  <h2>Overavanje recepata</h2>
	</div>
   <table align="left" class="table">
		<tr>
		   <th>ID</th>
		   <th>Dijagnoza</th>
		   <th>Lekar</th>
		   <th>Lekovi</th>
		   <th>Overavanje</th>
		</tr>
		<tr v-for="r in recepti" >
			<td class="myclass">{{r.id}}</td>
			<td class="myclass">{{r.dijagnoza}}</td>
			<td class="myclass">{{r.lekar}}</td>
			<td class="myclass">{{r.lekovi}}</td>
			<td><input class="btn-recept-overa" value='Overi' type='button' v-on:click="overi(r.id)"/></td>
		</tr>
   </table>
			
</div>
</div>		  
`
	, 
	methods : {
		overi(id) {
			this.id=id;
			axios
			.put('rest/recept/'+this.id)
			.then(response => {
				axios
			    .get('rest/recept')
			    .then(response => (this.recepti=response.data));
			});
		}
	},
	mounted(){
		console.log("Ucitala se overavanje komponenta");
		axios
	    .get('rest/login/getConcreteUser/MedicinskaS')
	    .then((response) => {
	    	console.log(response.data);	
	    })
	    .catch(response => {
			this.$router.push("/");
		});
		axios
	    .get('rest/recept')
	    .then(response => (this.recepti=response.data));
	},	
});