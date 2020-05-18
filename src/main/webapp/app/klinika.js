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
	    	izmena:{
	    		naziv: "",
                opis: "",
                adresa: ""
	    	}
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
		   <th>Izmena</th>
		   <th>Brisanje</th>
		</tr>
		<tr v-for="k in klinike" >
			<td class="myclass">{{k.id}}</td>
			<td class="myclass">{{k.naziv}}</td>
			<td class="myclass">{{k.adresa}}</td>
			<td class="myclass">{{k.opis}}</td>
			<td><input class="btn btn-warning btn-lg" value='Izmeni' type='button' v-on:click="izmeni(k.id,k.naziv,k.adresa,k.opis)"/></td>
			<td><input class="btn btn-danger btn-lg" value='Obrisi' type='button' v-on:click="obrisi(k.id)"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="text" class="fotrol" v-model="input.naziv" placeholder="Naziv"></td>
			<td><input type="text" class="fotrol" v-model="input.adresa" placeholder="Adresa"></td>
			<td><input type="text" class="fotrol" v-model="input.opis" placeholder="Opis"></td>
			<td><input class="btn btn-success" type='button' value='Dodaj'  v-on:click="dodaj()"/></td>
		</tr>	
   </table>
   
   <div id="modaldark">
		<div class="form-popup" id="myForm">
			<h6>ID:{{this.id}}</h6>
			<input type="text" class="psw" v-model="izmena.naziv">
			<input type="text" class="psw" v-model="izmena.adresa">
			<input type="text" class="psw" v-model="izmena.opis">
			</br></br>
			<button type="button" class="btn maal leftbutton" v-on:click="potvrda()">Potvrdi</button>
			<button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
		</div>
	</div>
			
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
        },
        obrisi(id){
        	axios
        	.delete('rest/klinika/'+id)
        	.then(response => {
				axios
					.get('rest/klinika')
					.then(response => (this.klinike=response.data))
			});
        },
        izmeni(id,naziv,adresa,opis){
        	this.id = id;
        	this.izmena.naziv = naziv;
        	this.izmena.adresa = adresa;
        	this.izmena.opis = opis;
        	document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
        },
        potvrda(){
        	axios
        	.put("rest/klinika/izmeni", {"id":this.id,
        		"naziv":this.izmena.naziv, "adresa":this.izmena.adresa, "opis":this.izmena.opis})
        	.then(response => {
    			axios
    				.get('rest/klinika')
    				.then(response => (this.klinike=response.data))
    		});
        	this.otkazi();
        },
        otkazi(){
        	document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
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