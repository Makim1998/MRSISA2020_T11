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
			<td><input class="btn btn-success" type='button' value='Dodaj'  v-on:click="dodaj()"/></td>
		</tr>	
   </table>
</div>
</div>		  
`
	, 
	methods : {
		dodaj() {
			if (!this.proveraPolja())
				alert("Niste uneli sva polja za dodavanje nove klinike!");
			else{
				console.log("Opis " + this.input.opis);
				axios
        		.post('rest/klinika/dodaj', {"id": null, "naziv":this.input.naziv,
        			"adresa":this.input.adresa, "opis":this.input.opis,
        			"prosecnaOcena":"nema"})
        		.then(response => {
					axios
						.get('rest/klinika')
						.then(response => (this.klinike=response.data))
				});
			}
        },
        proveraPolja(){
        	if (this.input.naziv == "" || this.input.adresa == "" || this.input.opis == "")
        		return false;
        	else
        		return true;
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
	    .get('rest/klinika')
	    .then(response => (this.klinike=response.data));
	},	
});