Vue.component("zakazaniPregledi", {
	data: function () {
	    return {
	    	lekar:null,
	    	lekar_id:null,
	    	lekar_username:"",
	    	klinika_id:"",
	    	input: {	    		 
                 datum: null,
                 trajanje:null,
                 tipPregleda:"",
                 sala:"",
                 lekar:"",
                 cena:""
             		},
	    	pregledi:[],
	    	sale:[],
	    	lekari:[],
	    	tipoviPregleda:[],
	    	cenovnik:{
	    		id:null,
	    		stavke:[],
	    		klinika_id:null,
	    	},
	    	id:null,
	    	trenutniKarton:[]
	    }
	},
	template: ` 
<div class="oneoption">
<div>
	<div class="jumbotron">
	  <h2>Zakazani termini za pregelede</h2>
	  <p>Pregled termina i zapocinjanje pregleda.</p> 
	</div>
   <table align="left" class="table">
		<tr>
		   <th>Datum i vreme pregleda</th>
		   <th>Tip pregleda</th>
		   <th>Sala</th>
		   <th>Lekar</th>
		   <th>Cena</th>
		   <th>Zdravstveni karton</th>
		   <th>Pregled</th>
		</tr>
		<tr v-for="tp in pregledi" class="filterDiv ">
			<td class="myclass">{{tp.datum.substring(0,10)}} {{tp.datum.substring(11,16)}}</td>
			<td class="myclass">{{tp.tip.naziv}}</td>
			<td class="myclass">{{tp.sala.naziv}}</td>
			<td class="myclass">{{tp.lekar.username}}</td>
			<td class="myclass">{{tp.cena.cena}}</td>
			<td><input class="btn btn-success" type='button' value='Detalji'  v-on:click="karton(tp.karton)"/></td>
			<td><input class="btn btn-success" value='Zapocnite' type='button' v-on:click="zapocni()"/></td>
		</tr>	
   </table>
   <div id="modaldark">
   <div id="myForm">
   <div id="pkarton">
       <form>
		<h2 class="text-center">Zdravstveni karton</h2>       
		<div class="lform-group">
			<label for="ime">Ime: </label>
   			<input type="text" id = "username" class="form-control" v-model="trenutniKarton.ime"  disabled>
		</div>
		<div class="lform-group">
			<label for="prezime">Prezime: </label>
			<input type="text" id = "prezime" class="form-control" v-model="trenutniKarton.prezime" disabled>
    	</div>
    	<div class="lform-group">
			<label for="pol">Pol: </label>
   			<input type="text" id = "pol" class="form-control" v-model="trenutniKarton.pol" disabled>
		</div>
		<div class="lform-group">
			<label for="rodj">Datum rodjenja: </label>
    		<input type="text" id = "rodj" class="form-control"  v-model="trenutniKarton.datumRodjenja" disabled>
		</div>
		<div class="lform-group">
			<label for="grupa">Krvna grupa: </label>
    		<input type="text"  id = "grupa" class="form-control" v-model="trenutniKarton.krvnaGrupa" disabled>
		</div>
     </form>
     <input class="btn btn-success pkartonbtn" value='OK' type='button' v-on:click="zatvoriKarton()"/>
   </div>
   </div>
   </div>
</div>
</div>		  
`
	, 
	methods : {
		karton(k) {
			this.trenutniKarton=k;
			document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
			document.getElementById("zkarton").style.display = "block";
        },
		zatvoriKarton() {
			document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
			document.getElementById("zkarton").style.display = "none";
        },
	},
	mounted(){
		axios
		.get('rest/login/getConcreteUser/Lekar')
	    .then((response) => {
			axios
		    .get('rest/Pregled/zakazani/'+response.data.id,response.data.id)
		    .then(response => (this.pregledi=response.data))
			.catch(response => {
				this.$router.push("/");
			});
	    	this.lekar=response.data;
	    	this.lekar_id=response.data.id;
	    	this.lekar_username=response.data.username;
	    	this.klinika_id=response.data.kc_id;
	    })
	    .catch(response => {
			this.$router.push("/");
		});
	},
});