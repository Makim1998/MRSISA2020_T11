Vue.component("zakazaniPregledi", {
	data: function () {
	    return {
	    	lekar:null,
	    	lekar_id:null,
	    	lekar_username:"",
	    	klinika_id:"",
	    	input: {	
	    		 karton:null,
                 datum: null,
                 trajanje:null,
                 tipPregleda:"",
                 sala:"",
                 lekar:"",
                 cena:"",
                 lekari:[]
             		},
	    	pregledi:[],
	    	sale:[],
	    	lekari:[],
	    	kc_id:null,
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
			<td><input class="btn btn-success" value='Zapocnite' type='button' v-on:click="zapocni(tp.karton)"/></td>
		</tr>	
   </table>
   <div id="modaldark">
   <div id="myForm4">
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
   <div id="myForm">
   <div id="zapocniP">
	   <div id="btns">
	     <input class="btn btn-danger" value='Otkazi' type='button' v-on:click="otkazi()"/>
	     <input class="btn btn-success" value='Zavrsite' type='button' v-on:click="otkazi();poruka();zavrsite();"/>
	   </div>
   </div>
   </div>
   <div class="form-popup" id="myForm2">
    <h4>Novi pregled/operacija</h4>
    <label for="od">Datum:<input type="datetime-local" id="od" class="psw"  placeholder="Datum" required v-model="input.datum"></label>
	<br>
	<label for="tra">Trajanje:<br>
    <input type="number" id="tra" class="psw"  placeholder="minute"  min="10" max="60" required v-model="input.trajanje">
    </label>
    <br>
	<label for="ca">Cena:<br>
		<select id="ca" v-model="input.cena">
			<option v-for="ca in cenovnik.stavke">{{ca.usluga}}-{{ca.cena}}DIN-ID:{{ca.id}}</option>
		</select>
	</label>
    </br></br>
    <button type="button" class="btn maal leftbutton" v-on:click="zakaziPregled()">Pregled</button>
    <button type="button" class="btn maal leftbutton" style="margin-left:5px" v-on:click="zakaziOperaciju()">Operacija</button>
    <button type="button" class="btn zaal rightbutton" v-on:click="otkazi2()">Otkazi</button>
   </div>
    <div id="myForm3">
   <div id="poruka">
		<h4>Pregled je zavrsen</h4>
		<p>Da li zelite da zakazate novi pregled/operaciju?</p>
	   <input class="btn btn-success leftbutton" value='Da' type='button' v-on:click="porukane();zavrsi();"/>
	   <input class="btn btn-danger rightbutton" value='Ne' type='button' v-on:click="porukane()"/>
   </div>
   </div>
   </div>
</div>
</div>		  
`
	, 
	methods : {
		zapocni(a){
			this.input.karton=a;
			document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
			document.getElementById("zapocniP").style.display="block";
		},
		otkazi(){
			document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
			document.getElementById("zapocniP").style.display="none";
		},
		zavrsi(){
			document.getElementById("myForm2").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
		},
		otkazi2(){
			document.getElementById("myForm2").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
		},
		poruka(){
			document.getElementById("myForm3").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
		},
		porukane(){
			document.getElementById("myForm3").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
		},
		zakaziOperaciju() {
        	this.input.lekari=[];
        	this.input.lekari.push(this.input.lekar);
        	axios
        	.post('rest/Operacija/dodaj', {"id":null,"datum":this.input.datum,"karton":this.input.karton,
        		"trajanje":this.input.trajanje,"cena":this.input.cena,
        		"sala":null,"lekari":this.input.lekari,})
            .then(response =>{
            	alert("Uspesno ste zakazali operaciju");
            	this.otkazi2()
            })
			.catch(error => {
				alert("Nevalidan unos. Pokusajte ponovo.");
			});
        },
		zakaziPregled() {
        	axios
        	.post('rest/Pregled/dodaj', {"id":null,"datum":this.input.datum,"karton":this.input.karton,
        		"trajanje":this.input.trajanje,"tip":null,"cena":this.input.cena,
        		"sala":null,"lekar":this.input.lekar,})
            .then(response =>{
            	alert("Uspesno ste zakazali pregled");
            	this.otkazi2()
            })
			.catch(error => {
				alert("Nevalidan unos. Pokusajte ponovo.");
			});
        },
		karton(k) {
			this.trenutniKarton=k;
			document.getElementById("myForm4").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
			document.getElementById("pkarton").style.display = "block";
        },
		zatvoriKarton() {
			document.getElementById("myForm4").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
			document.getElementById("pkarton").style.display = "none";
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
			this.input.lekar=response.data;
	    	this.lekar=response.data;
	    	this.lekar_id=response.data.id;
	    	this.lekar_username=response.data.username;
	    	this.klinika_id=response.data.kc_id;
	    })
	    .catch(response => {
			this.$router.push("/");
		});
		axios
	    .get('rest/login/getKlinika')
	    .then((response) => {;
	    	this.kc_id=response.data.id;
			axios
		    .get('rest/cenovnik/'+this.kc_id,this.kc_id)
		    .then(response =>{
		    	this.cenovnik.id=response.data.id;
		    	this.cenovnik.stavke = response.data.stavke;
		    	this.cenovnik.klinika_id = response.data.klinikaID;
		    });
		})
		.catch(response => {
			this.$router.push("/");
		});
	},
});