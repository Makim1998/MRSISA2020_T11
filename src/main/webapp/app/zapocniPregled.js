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
	    	zavrseniPregledi:[],
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
	    	trenutniKarton:[],
	    	dijagnoze:[],
	    	lekovi:[],
	    	noviLek: "",
	    	izvestaj: {
	    		dijagnoza: "",
	    		lekovi: "sifre dodatih lekova: ",
	    		opis: "",
	    		visina: "",
	    		tezina: "",
	    		krvna: "",
	    		alergije: ""
	    	},
	    	zapocniOdg:null
	    }
	},
	template: ` 
<div class="oneoptionlekar">
<div>
	<h2 class="text-center">Zakazani termini za preglede</h2>
<br>
   <table align="left" class="table klasicna-tabela">
		<tr>
		   <th>Datum i vreme pregleda</th>
		   <th>Tip pregleda</th>
		   <th>Sala</th>
		   <th>Pacijent</th>
		   <th>Cena</th>
		   <th>Zdravstveni karton</th>
		   <th>Pregled</th>
		</tr>
		<tr v-for="tp in pregledi" class="filterDiv ">
			<td class="myclass">{{tp.datum.substring(0,10)}} {{tp.datum.substring(11,16)}}</td>
			<td class="myclass">{{tp.tip.naziv}}</td>
			<td class="myclass">{{tp.sala.naziv}}</td>
			<td class="myclass">{{tp.karton.ime+" "+tp.karton.prezime}}</td>
			<td class="myclass">{{tp.cena.cena}}</td>
			<td><input class="btn btn-primary" type='button' value='Detalji'  v-on:click="karton(tp.karton)"/></td>
			<td><input class="btn btn-primary" value='Zapocnite' type='button' v-on:click="zapocni(tp.karton, tp.id)"/></td>
		</tr>	
   </table>
   <h2 class="text-center">Zavrseni pregledi</h2>
<br>
   <table align="left" class="table klasicna-tabela">
		<tr>
		   <th>Datum i vreme pregleda</th>
		   <th>Tip pregleda</th>
		   <th>Sala</th>
		   <th>Pacijent</th>
		   <th>Cena</th>
		   <th>Zdravstveni karton</th>
		   <th>Pregled</th>
		</tr>
		<tr v-for="tp in zavrseniPregledi" class="filterDiv ">
			<td class="myclass">{{tp.datum.substring(0,10)}} {{tp.datum.substring(11,16)}}</td>
			<td class="myclass">{{tp.tip.naziv}}</td>
			<td class="myclass">{{tp.sala.naziv}}</td>
			<td class="myclass">{{tp.karton.ime+" "+tp.karton.prezime}}</td>
			<td class="myclass">{{tp.cena.cena}}</td>
			<td><input class="btn btn-primary" type='button' value='Detalji'  v-on:click="karton(tp.karton)"/></td>
			<td><input class="btn btn-primary" value='Izmenite' type='button' v-on:click="zapocni(tp.karton, tp.id)"/></td>
		</tr>	
   </table>
         <!-- Modal -->
<div class="modal fade" id="novipregled" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" >Novi pregled/operacija</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form>
			<div class="form-group">
		      	<label for = "od">Datum: </label>
				<input type="datetime-local" id="od" class="psw"  required v-model="input.datum">
		    </div>
		    <div class="form-group">
				<label for="tra">Trajanje:</label>
			    <input type="number" id="tra" class="psw" min="10" max="60" required v-model="input.trajanje">		    
			</div>
		    <div class="form-group">
		      	<label for="ca">Cena:</label>
					<select id="ca" v-model="input.cena">
						<option v-for="ca in cenovnik.stavke">{{ca.usluga}}-{{ca.cena}}DIN-ID:{{ca.id}}</option>
					</select>
		    </div>
		</form>
      </div>
      <div class="modal-footer">
          <button type="button" class="btn btn-primary" v-on:click="zakaziPregled()">Pregled</button>
		  <button type="button" class="btn btn-primary" v-on:click="zakaziOperaciju()">Operacija</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal" v-on:click="otkazi2()">Otkazi</button>
      </div>
    </div>
  </div>
</div>
   <div id="modaldark" style="overflow-y:scroll;">
   <div id="myForm4">
   <div id="pkarton">
       <form>
		<h2 class="text-center">Zdravstveni karton</h2>       
    	<div class="lform-group">
			<label for="pol">Pol: </label>
   			<input type="text" id = "pol" class="form-control" v-model="trenutniKarton.pol" disabled>
		</div>
		<div class="lform-group">
			<label for="rodj">Datum rodjenja: </label>
    		<input type="text" id = "rodj" class="form-control"  v-model="trenutniKarton.datumRodjenja" disabled>
		</div>
		<div class="lform-group">
			<label for="visina">Visina: </label>
    		<input type="text"  id = "visina" class="form-control" v-model="trenutniKarton.visina" disabled>
		</div>
		<div class="lform-group">
			<label for="tezina">Tezina: </label>
    		<input type="text"  id = "tezina" class="form-control" v-model="trenutniKarton.tezina" disabled>
		</div>
		<div class="lform-group">
			<label for="grupa">Krvna grupa: </label>
    		<input type="text"  id = "grupa" class="form-control" v-model="trenutniKarton.krvnaGrupa" disabled>
		</div>
		<div class="lform-group">
			<label for="alergije">Alergije: </label>
    		<input type="text"  id = "alergije" class="form-control" v-model="trenutniKarton.alergije" disabled>
		</div>
		<div class="lform-group">
			<label for="propisano">Propisani lek(ovi): </label>
    		<input type="text"  id = "propisano" class="form-control" v-model="trenutniKarton.propisano" disabled>
		</div>
		<div class="lform-group">
			<label for="istorija">Istorija bolesti: </label>
    		<textarea style="height:80px;" id = "istorija" class="form-control" v-model="trenutniKarton.istorijaBolesti" disabled></textarea>
		</div>
     </form>
     <input class="btn btn-success pkartonbtn" value='OK' type='button' v-on:click="zatvoriKarton()"/>
   </div>
   </div>
   <div id="myForm">
   <div id="zapocniP">
	   <h3 style="text-align: center;">Izvestaj o pregledu</h3>
	   <br></br>
	   <div>
	   <table class="dijagnoze-sifre">
	     <tr>
	       <th colspan="2" style="text-align: center;">Sifre za dijagnoze</th>
	     </tr>
	     <tr style="height:40px;"></tr>
	     <tr v-for="d in dijagnoze">
	       <td>{{d.split(" - ")[0]+":"}}</td>
	       <td>{{"--"+d.split(" - ")[1]}}</td>
	     </tr>
	   </table>
	   
	   <table class="zapocni-input">
	   <tr>
		 <td width="28%"><label>Sifra za dijagnozu:</label></td>
		 <td width="2%"></td>
		 <td colspan="2" width="70%"><input type="text" style="width:100%;" v-model="izvestaj.dijagnoza"></td>
	   </tr>
	   <tr style="height:20px;"></tr>
	   <tr><td colspan="4" style="text-align: center;">(Lekovi nisu obavezni za dodati)</td></tr>
	   <tr>
	     <td width="28%"><label>Sifra za lek:</label></td>
		 <td width="2%"></td>
		 <td width="50%"><input type="text" style="width:100%;" v-model="noviLek"></td>
		 <td width="10%"><input class="btn-sifra-lek-add" type="button" v-on:click="dodajLek()" value="dodaj"></td>
	   </tr>
	   <tr>
	     <td colspan="3" style="text-align: center;">{{izvestaj.lekovi}}</td>
	     <td><input type="button" class="btn-ponisti-recept" v-on:click="ponistiRecept()" value="ponisti"></td>
	   </tr>
	   <tr style="height:40px;"></tr>
	   
	   <tr>
		 <td width="28%"><label>Visina:</label></td>
		 <td width="2%"></td>
		 <td colspan="2" width="70%"><input type="text" style="width:100%;" v-model="izvestaj.visina"></td>
	   </tr>
	   <tr>
		 <td width="28%"><label>Tezina:</label></td>
		 <td width="2%"></td>
		 <td colspan="2" width="70%"><input type="text" style="width:100%;" v-model="izvestaj.tezina"></td>
	   </tr>
	   <tr>
		 <td width="28%"><label>Alergije:</label></td>
		 <td width="2%"></td>
		 <td colspan="2" width="70%"><input type="text" style="width:100%;" v-model="izvestaj.alergije"></td>
	   </tr>
	   <tr>
		 <td width="28%"><label>Krvna grupa:</label></td>
		 <td width="2%"></td>
		 <td colspan="2" width="70%"><input type="text" style="width:100%;" v-model="izvestaj.krvna"></td>
	   </tr>
	   <tr>
		 <td width="28%"><label>Istorija bolesti pacijenta:</label></td>
		 <td width="2%"></td>
		 <td width="70%" colspan="2"><textarea v-model="izvestaj.opis" style="display:block; width:100%; height:100px;">
		 </textarea></td>
	   </tr>
	   </table>
	   
	   <table class="lekovi-sifre">
	     <tr>
	       <th colspan="2" style="text-align: center;">Sifre za lekove</th>
	     </tr>
	     <tr style="height:40px;"></tr>
	     <tr v-for="l in lekovi">
	       <td>{{l.split(" - ")[0]+":"}}</td>
	       <td>{{"--"+l.split(" - ")[1]}}</td>
	     </tr>
	   </table>
	   
	   </div>
	   <div id="btns">
	     <input class="btn btn-secondary" value='Otkazi' type='button' v-on:click="otkazi()"/>
	     <input class="btn btn-primary" value='Zavrsite' type='button' v-on:click="zapocniPregled();"/>
	   </div>
   </div>
   </div>
   <!---div class="form-popup" id="myForm2">
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
   </div--->
    <div id="myForm3">
   <div id="poruka">
		<h4>Pregled je zavrsen</h4>
		<p>Da li zelite da zakazate novi pregled/operaciju?</p>
	   <input class="btn btn-success leftbutton" value='Da' type='button' data-toggle="modal" data-target="#novipregled" v-on:click="porukane();zavrsi();"/>
	   <input class="btn btn-danger rightbutton" value='Ne' type='button' v-on:click="porukane()"/>
   </div>
   </div>
   </div>
</div>
</div>		  
`
	, 
	methods : {
		zapocni(a,id){
			this.input.karton=a;
			this.id=id;
			this.izvestaj.opis=a.istorijaBolesti;
			this.izvestaj.visina=a.visina;
			this.izvestaj.tezina=a.tezina;
			this.izvestaj.alergije=a.alergije;
			this.izvestaj.krvna=a.krvnaGrupa;
			//alert("Nije implementirano");
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
		dodajLek(){
			this.izvestaj.lekovi+=" "+this.noviLek;
		},
		ponistiRecept(){
			this.izvestaj.lekovi="sifre dodatih lekova: ";
		},
		zapocniPregled(){
			if (this.izvestaj.dijagnoza.trim() == "")
				alert("Niste uneli sifru za dijagnozu!");
			else if (this.izvestaj.visina.trim() == "")
				alert("Visina pacijenta ne moze da ostane prazna!");
			else if (this.izvestaj.tezina.trim() == "")
				alert("Tezina pacijenta ne moze da ostane prazna!");
			else if (this.izvestaj.krvna.trim() == "")
				alert("Krvna grupa pacijenta ne moze da ostane prazna!");
			else{
				console.log(this.izvestaj.lekovi);
				axios
				.put('rest/Pregled/zapocni?id='+this.id+'&sifra='+this.izvestaj.dijagnoza.trim()+"&istorija="+this.izvestaj.opis.trim()+"&lekovi="+this.izvestaj.lekovi+"&visina="+this.izvestaj.visina+"&tezina="+this.izvestaj.tezina+"&alergije="+this.izvestaj.alergije+"&krvna="+this.izvestaj.krvna)
				.then(response => {
					this.zapocniOdg=response.data;
					if (this.zapocniOdg){
						axios
						.get('rest/login/getConcreteUser/Lekar')
					    .then((response) => {
							axios
						    .get('rest/Pregled/zakazani/'+response.data.id,response.data.id)
						    .then(response => {this.pregledi=response.data;})
							.catch(response => {
								this.$router.push("/");
							});
							axios
						    .get('rest/Pregled/zavrseniLekar/'+response.data.id,response.data.id)
						    .then(response => {this.zavrseniPregledi=response.data;})
							.catch(response => {
								this.$router.push("/");
							});
							this.input.lekar=response.data;
					    	this.lekar=response.data;
					    	this.lekar_id=response.data.id;
					    	this.lekar_username=response.data.username;
					    	this.klinika_id=response.data.kc_id;
					    	this.izvestaj.lekovi="sifre dodatih lekova: ";
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
						});
						this.zavrsiPregled();
					}
					else
						alert("Ne postoji dijagnoza sa unetom sifrom");
				});
			}
		},
		zavrsiPregled(){
			document.getElementById("myForm").style.display = "none";
			document.getElementById("zapocniP").style.display = "none";
			this.poruka();
		},
		zavrsi(){
			/*document.getElementById("myForm2").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";*/
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
			if(!moment( $("#od").val(), 'YYYY-MM-DDTHH:mm', true).isAfter(moment())){
        		alert("Odaberite datum i vreme u buducnosti!");
        		return;
        	}
			if(!moment( $("#od").val(), 'YYYY-MM-DDTHH:mm', true).isValid()){
        		alert("Datum nije u ispravnom formatu!\n (YYYY-MM-DD HH:mm)");
        		return;
        	}
        	this.input.lekari=[];
        	this.input.lekari.push(this.input.lekar);
        	axios
        	.post('rest/Operacija/dodaj', {"id":null,"datum":this.input.datum,"karton":this.input.karton,
        		"trajanje":this.input.trajanje,"cena":this.input.cena,
        		"sala":null,"lekari":this.input.lekari,})
            .then(response =>{
            	alert("Uspesno ste zakazali operaciju");
            	$('#novipregled').modal('hide');
            	$('.modal-backdrop').remove();            })
			.catch(error => {
				alert("Nevalidan unos. Pokusajte ponovo.");
			});
        },
		zakaziPregled() {
			if(!moment( $("#od").val(), 'YYYY-MM-DDTHH:mm', true).isAfter(moment())){
        		alert("Odaberite datum i vreme u buducnosti!");
        		return;
        	}
			if(!moment( $("#od").val(), 'YYYY-MM-DDTHH:mm', true).isValid()){
        		alert("Datum nije u ispravnom formatu!\n (YYYY-MM-DD HH:mm)");
        		return;
        	}
        	axios
        	.post('rest/Pregled/dodaj', {"id":null,"datum":this.input.datum,"karton":this.input.karton,
        		"trajanje":this.input.trajanje,"tip":null,"cena":this.input.cena,
        		"sala":null,"lekar":this.input.lekar,})
            .then(response =>{
            	alert("Uspesno ste zakazali pregled");
            	$('#novipregled').modal('hide');
            	$('.modal-backdrop').remove();            })
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
		    .then(response => {this.pregledi=response.data;})
			.catch(response => {
				this.$router.push("/");
			});
			axios
		    .get('rest/Pregled/zavrseniLekar/'+response.data.id,response.data.id)
		    .then(response => {this.zavrseniPregledi=response.data;})
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
		axios
		.get('rest/sifarnik/dijagnoze')
		.then(response => (this.dijagnoze=response.data));
		axios
		.get('rest/sifarnik/lekovi')
		.then(response => (this.lekovi=response.data));
	},
});