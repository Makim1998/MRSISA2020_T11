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
	    	izmena: ""
	    }
	},
	template: ` 
<div class="oneoption">
<div>
	<div class="jumbotron">
	  <h2>Stavke sifarnika</h2>
	  <p>Dodavanje i izmena</p> 
	</div>
   <table align="left" class="table">
		<tr>
		   <th>ID</th>
		   <th>Sifra</th>
		   <th>ID Stavke</th>
		   <th>Tip stavke</th>
		   <th>Brisanje</th>
		</tr>
		<tr v-for="s in stavke" >
			<td class="myclass">{{s.id}}</td>
			<td class="myclass">{{s.sifra}}</td>
			<td class="myclass">{{s.stavkaId}}</td>
			<td class="myclass">{{s.tip}}</td>
			<td><input class="btn btn-danger btn-lg" value='Obrisi' type='button' v-on:click="obrisi(s.id)"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="text" class="fotrol" v-model="input.sifra" placeholder="Sifra"></td>
			<td><input type="number" min="1" max="1000" class="fotrol" v-model="input.stavkaId" placeholder="StavkaId"></td>
			<td><select v-model="input.tip">
				<option selected>LEK</option>
				<option>DIJAGNOZA</option>
			</select></td>
			<td><input class="btn btn-success" type='button' value='Dodavanje'  v-on:click="proveraPolja()"/></td>
		</tr>
		<tr>
			<td></td>
			<td><select v-model="id">
				<option v-for="s in stavke">{{s.id}}</option>
			</select></td>
			<td><input type="text" class="fotrol" v-model="izmena" placeholder="Nova sifra"></td>
			<td></td>
			<td><input class="btn btn-warning btn-lg" type='button' value='Izmena' v-on:click="izmeni()"/></td>
		</tr>
   </table>
</div>
</div>		  
`
	, 
	methods : {
		proveraPolja(){
			if (this.input.sifra.trim()=="" || this.input.stavkaId=="" || this.input.tip=="")
				alert("Niste uneli sva polja!");
			else
				this.dodaj();
		},
		dodaj() {
        	axios
        	.post('rest/sifarnik/dodaj', {"id": null, "sifra":this.input.sifra, "stavkaId":this.input.stavkaId, "tip":this.input.tip})
        	.then(response => {
    			axios
    				.get('rest/sifarnik')
    				.then(response => (this.stavke=response.data))
    		});
        },
        izmeni(){
        	if (this.izmena=="")
        		alert('Nova sifra ne moze da bude prazna!')
        	else{
        		axios
        		.put('rest/sifarnik/'+this.id+"/"+this.izmena)
        		.then(response => {
    				axios
    					.get('rest/sifarnik')
    					.then(response => (this.stavke=response.data))
    			});
        	}
        },
        obrisi(id){
        	axios
        	.delete('rest/sifarnik/'+id)
        	.then(response => {
				axios
					.get('rest/sifarnik')
					.then(response => (this.stavke=response.data))
			});
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