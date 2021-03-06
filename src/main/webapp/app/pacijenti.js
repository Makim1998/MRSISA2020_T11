Vue.component("pacijenti", {
	data: function () {
	    return {
	    	input: {	 
	    		karton:null,
                datum: null,
                trajanje:null,
                tipPregleda:"",
                sala:"",
                lekar:"",
                cena:"",
                lekari:[],
                pacijent:null
            		},
	    	tipovi:[],
	    	pacijent:[],
	    	karton:[],
	    	id:null,
	    	izmena:"",
	    	klinika_id:null,
	    	kc_id:null,
	    	cenovnik:{
	    		id:null,
	    		stavke:[],
	    		klinika_id:null,
	    	},
	    	lekar_username:null,
	    	pregledi:[],
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
	    	zapocniOdg:null,
	    	pregled_id:null
	    }
	},
	template: ` 
<div class="oneoptionsestra">
<div>
<h2 class="text-center">Pacijenti</h2>
<br>
   <table align="left" class="table klasicna-tabela">
   		<tr>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput1" placeholder="Ime"></th>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput2" placeholder="Prezime"></th>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput3" placeholder="Broj osiguranika"></th>
		   <th><input class="btn btn-primary"  type='button' value='Pretrazi'  v-on:click="fjaPretrage()"/></th>
		   <th><input class="btn btn-primary"  type='button' value='Prikazi sve'  v-on:click="prikaziSve()"/></th>
		</tr>
		<tr>
		   <th>Ime</th>
		   <th>Prezime</th>
		   <th>Broj osiguranika</th>
		   <th></th>
		   <th>Profil</th>
		</tr>
		<tr v-for="tp in tipovi" v-if="tp.kc_id==klinika_id" class="filterDiv ">
			<td class="myclass">{{tp.ime}}</td>
			<td class="myclass">{{tp.prezime}}</td>
			<td class="myclass">{{tp.brojOsiguranika}}</td>
			<td></td>
			<td><input class="btn btn-primary" style="margin-top:10px;" type='button' value='Pacijent'  v-on:click="prikaziPacijenta(tp,tp.karton)"/></td>
		</tr>
   </table>
   
   <div id="modaldark" style="overflow-y:scroll;">
   
   <div id="myForm5">
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
	     <input class="btn btn-secondary" value='Otkazi' type='button' v-on:click="otkazi2()"/>
	     <input class="btn btn-primary" value='Zavrsite' type='button' v-on:click="zapocniPregled();"/>
	   </div>
   </div>
   </div>
   
   <div id="myForm">
   <div id="lkarton">
    <form>
		<h2 class="text-center">Profil pacijenta</h2>       
		<div class="lform-group">
			<label for="ime">Email: </label>
   			<input type="text" id = "username" class="form-control" v-model="pacijent.email"  disabled>
		</div>
		<div class="lform-group">
			<label for="prezime">Ime: </label>
			<input type="text" id = "prezime" class="form-control" v-model="pacijent.ime" disabled>
    	</div>
    	<div class="lform-group">
			<label for="pol">Prezime: </label>
   			<input type="text" id = "pol" class="form-control" v-model="pacijent.prezime" disabled>
		</div>
		<div class="lform-group">
			<label for="rodj">Adresa: </label>
    		<input type="text" id = "rodj" class="form-control"  v-model="pacijent.adresa" disabled>
		</div>
		<div class="lform-group">
			<label for="grupa">Grad: </label>
    		<input type="text"  id = "grupa" class="form-control" v-model="pacijent.grad" disabled>
		</div>
		<div class="lform-group">
			<label for="grupa">Drzava: </label>
    		<input type="text"  id = "grupa" class="form-control" v-model="pacijent.drzava" disabled>
		</div>
		<div class="lform-group">
			<label for="grupa">Jedinstveni br. osiguranika: </label>
    		<input type="text"  id = "grupa" class="form-control" v-model="pacijent.brojOsiguranika" disabled>
		</div>
     </form>
     <form id="zkarton">
		<h2 class="text-center">Zdravstveni karton</h2>       
    	<div class="lform-group">
			<label for="pol">Pol: </label>
   			<input type="text" id = "pol" class="form-control" v-model="karton.pol" disabled>
		</div>
		<div class="lform-group">
			<label for="rodj">Datum rodjenja: </label>
    		<input type="text" id = "rodj" class="form-control"  v-model="karton.datumRodjenja" disabled>
		</div>
		<div class="lform-group">
			<label for="visina">Visina: </label>
    		<input type="text"  id = "visina" class="form-control" v-model="karton.visina" disabled>
		</div>
		<div class="lform-group">
			<label for="tezina">Tezina: </label>
    		<input type="text"  id = "tezina" class="form-control" v-model="karton.tezina" disabled>
		</div>
		<div class="lform-group">
			<label for="grupa">Krvna grupa: </label>
    		<input type="text"  id = "grupa" class="form-control" v-model="karton.krvnaGrupa" disabled>
		</div>
		<div class="lform-group">
			<label for="alergije">Alergije: </label>
    		<input type="text"  id = "alergije" class="form-control" v-model="karton.alergije" disabled>
		</div>
		<div class="lform-group">
			<label for="propisano">Propisani lek(ovi): </label>
    		<input type="text"  id = "propisano" class="form-control" v-model="karton.propisano" disabled>
		</div>
		<div class="lform-group">
			<label for="istorija">Istorija bolesti: </label>
    		<textarea style="height:100px;" id = "istorija" class="form-control" v-model="karton.istorijaBolesti" disabled></textarea>
		</div>
     </form>
     <div class="buttons">
     	<button type="button" id = "zavrsiPregled" style="display:none;" class="btn  btn-primary" v-on:click="otkazi();poruka();zavrsite();">Zavrsi pregled</button>
		<button type="button" id = "pKartona" style="display:none;" class="btn  btn-primary" v-on:click="prikaziKarton()">Prikazi karton</button>
		<button type="button" id = "zPregled" style="display:none;" class="btn  btn-primary" v-on:click="zapocni()">Zapocni pregled</button>
		<button type="button" id = "cancelBtn" class="btn btn-secondary" v-on:click="otkazi()">Otkazi</button>
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
	   <input class="btn btn-danger rightbutton" value='Ne' type='button' v-on:click="porukane();otkazi();"/>
   </div>
   </div>
   </div>
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
          <button type="button" class="btn btn-secondary" data-dismiss="modal" v-on:click="otkazi()">Otkazi</button>
      </div>
    </div>
  </div>
</div>
</div>
</div>		  
`
	, 
	methods : {
		zapocni(){
			//alert("Nije implementirano");
			//document.getElementById("pKartona").style.display="none";
			//document.getElementById("zPregled").style.display="none";
			//document.getElementById("zkarton").style.display="block";
			//document.getElementById("zavrsiPregled").style.display="block";
			document.getElementById("myForm").style.display="none";
			document.getElementById("zapocniP").style.display="block";
			document.getElementById("myForm5").style.display="block";
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
				.put('rest/Pregled/zapocni?id='+this.pregled_id+'&sifra='+this.izvestaj.dijagnoza.trim()+"&istorija="+this.izvestaj.opis.trim()+"&lekovi="+this.izvestaj.lekovi+"&visina="+this.izvestaj.visina+"&tezina="+this.izvestaj.tezina+"&alergije="+this.izvestaj.alergije+"&krvna="+this.izvestaj.krvna)
				.then(response => {
					this.zapocniOdg=response.data;
					if (this.zapocniOdg){
						axios
						.get('rest/login/getConcreteUser/Lekar')
					    .then((response) => {
					    	this.input.lekar=response.data;
					    	this.lekar_username=response.data.username;
					    	this.klinika_id=response.data.kc_id;
					    })
					    .catch(response => {
					    	axios
							.get('rest/login/getConcreteUser/MedicinskaS')
						    .then((response) => {
						    	this.input.lekar=response.data;
						    	this.lekar_username=response.data.email;
						    	this.klinika_id=response.data.kc_id;
						    })
						    .catch(response => {
						    	this.$router.push("/");
						    });
						});
						axios
					    .get('rest/pacijent/svi')
					    .then(response => (this.tipovi=response.data));
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
						this.izvestaj.lekovi="sifre dodatih lekova: ";
						this.poruka();
					}
					else
						alert("Ne postoji dijagnoza sa unetom sifrom");
				});
			}
		},
		otkazi2(){
			document.getElementById("myForm5").style.display="none";
			document.getElementById("zapocniP").style.display = "none";
			document.getElementById("myForm").style.display="block";
			this.prikaziPacijenta(this.input.pacijent,this.input.karton);
		},
		poruka(){
			document.getElementById("myForm").style.display = "none";
			document.getElementById("myForm5").style.display = "none";
			document.getElementById("myForm3").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
		},
		porukane(){
			document.getElementById("myForm3").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
		},
		zavrsi(){
			/*document.getElementById("myForm2").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";*/
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
        	.post('rest/Operacija/dodaj', {"id":null,"datum":this.input.datum,
        		"trajanje":this.input.trajanje,"cena":this.input.cena,"karton":this.input.karton,
        		"sala":null,"lekari":this.input.lekari,})
            .then(response =>{
            	alert("Uspesno ste zakazali operaciju");
            	$('#novipregled').modal('hide');
            	$('.modal-backdrop').remove();
            })
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
		prikaziPacijenta(pacijent,karton){
        	this.input.karton=karton;
        	this.input.pacijent=pacijent;
        	this.pacijent=pacijent;
        	this.karton=karton;
        	document.getElementById("pKartona").style.display = "block";
			axios
		    .get('rest/Pregled/zakazani/'+this.input.lekar.id)
		    .then(response => {
				document.getElementById("myForm").style.display = "block";
				document.getElementById("modaldark").style.display = "block";
				document.getElementById("modaldark").style.opacity="1";
		    	this.pregledi=response.data;
		    	var duzina=this.pregledi.length;
		    	for(var i =0;i<duzina;i++){
		    		var obj=this.pregledi[i];
		    		if(obj.lekar.username==this.lekar_username && obj.karton!=null && obj.dijagnoza==null){
		    			if(obj.karton.id==this.karton.id){
			    			this.karton=obj.karton;
			    			document.getElementById("zPregled").style.display = "block";
			    			this.pregled_id=obj.id;
			    			this.izvestaj.opis=obj.karton.istorijaBolesti;
			    			this.izvestaj.visina=obj.karton.visina;
			    			this.izvestaj.tezina=obj.karton.tezina;
			    			this.izvestaj.alergije=obj.karton.alergije;
			    			this.izvestaj.krvna=obj.karton.krvnaGrupa;
		    			}
		    		}
		    	}
		    });	
		},
		prikaziKarton(){
			document.getElementById("zkarton").style.display = "block";
		},
		otkazi() {
			this.karton=[];
			//document.getElementById("zavrsiPregled").style.display="none";
			document.getElementById("pKartona").style.display = "none";
			document.getElementById("zPregled").style.display = "none";
			document.getElementById("zkarton").style.display = "none";
			document.getElementById("myForm").style.display = "none";
			document.getElementById("myForm5").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
        },
		obrisi(id) {
            axios
            .delete("rest/lekari/"+id,id)
            .then(response =>{		
				axios
			    .get('rest/lekari')
			    .then(response => (this.tipovi=response.data));
			})
			.catch(error => {
				alert("Lekar se ne moze obrisati,ima zakazan pregled.");
			});
        },
        prikaziSve(){
            var i,li;
            li = document.getElementsByClassName("filterDiv");
            for (i = 0; i < li.length; i++) {
          	  li[i].style.display = "";
            }
        },
        fjaPretrage() {
          var lista=[0,1,2];
          var listaduz=lista.length;
          var n,li,i;
          li = document.getElementsByClassName("filterDiv");
          for (i = 0; i < li.length; i++) {
        	  li[i].style.display = "";
          }
          for(var z=0; z<lista.length;z++){       	  
	          var input, filter, ul, a, txtValue;
	          n=lista[z];
	          if(n==0){
	        	  input = document.getElementById('myInput1');
	          }else if(n==1){
	        	  input = document.getElementById('myInput2');
	          }else{
	        	  input = document.getElementById('myInput3');
	          }
	          filter = input.value.toUpperCase();
	          li = document.getElementsByClassName("filterDiv");
	
	          for (i = 0; i < li.length; i++) {
	            a = li[i].getElementsByTagName("td")[n];
	            txtValue = a.textContent || a.innerText;
	            if (txtValue.toUpperCase().indexOf(filter) > -1) {
	              if(li[i].style.display != "none"){
	            	  li[i].style.display = "";
	              }
	            } else {
	              li[i].style.display = "none";
	            }
	          }
        	}
        }
	},
	mounted(){
		axios
		.get('rest/login/getConcreteUser/Lekar')
	    .then((response) => {
	    	this.input.lekar=response.data;
	    	this.lekar_username=response.data.username;
	    	this.klinika_id=response.data.kc_id;
	    })
	    .catch(response => {
	    	axios
			.get('rest/login/getConcreteUser/MedicinskaS')
		    .then((response) => {
		    	this.input.lekar=response.data;
		    	this.lekar_username=response.data.email;
		    	this.klinika_id=response.data.kc_id;
		    })
		    .catch(response => {
		    	this.$router.push("/");
		    });
		});
		axios
	    .get('rest/pacijent/svi')
	    .then(response => (this.tipovi=response.data));
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