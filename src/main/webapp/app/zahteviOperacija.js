Vue.component("zahteviOperacija", {
	data: function () {
	    return {
	    	pregledi:[],
	    	sale:[],
	    	operacije:[],
	    	klinika_id:null,
	    	trenutniKarton:[],
	    	admin:null,
	    	datumPretrage:"s",
	    	saljemPregled:null,
	    	ispitanPregled:null,
	    	sala:null,
	    	lekari:[],
	    	dodati:[],
	    	nepregledi:[],
	    	zaOdlaganje:null,
	    	noviDatum:null,
	    	sistem:[]
	    }
	},
	template: ` 
<div class="oneoption">
<div>
<h2 class="text-center">Zahtevi lekara za operaciju</h2>
<br>
   <table align="left" class="table klasicna-tabela">
		<tr>
		   <th>Datum i vreme operacije</th>
		   <th>Trajanje</th>
		   <th>Zdravstveni karton</th>
		   <th>Operacija</th>
		</tr>
		<tr v-for="tp in operacije" class="filterDiv ">
			<td class="myclass">{{tp.datum.substring(0,10)}} {{tp.datum.substring(11,16)}}</td>
			<td class="myclass">{{tp.trajanje}} minuta</td>
			<td><input class="btn btn-primary" type='button' value='Detalji'  v-on:click="karton(tp.karton)"/></td>
			<td v-if="sistem.indexOf(tp)==-1"><input class="btn btn-primary" value='Zakazite' type='button' v-on:click="zakazite(tp.datum,tp.trajanje,tp,0)"/></td>
			<td v-else><input class="btn btn-primary" value='Potvrdite' type='button' v-on:click="zakazite2(tp.datum,tp.trajanje,tp,0)"/></td>
		</tr>
	
</table>

	<br><br>
	
	<h2 class="text-center">Rezervisane operacije</h2>
	  <br>
	<table align="left" class="table klasicna-tabela">
	  <tr>
		   <th>Datum i vreme operacije</th>
		   <th>Trajanje</th>
		   <th>Zdravstveni karton</th>
		   <th>Operacija</th>
	  </tr>
	  <tr v-for="tp in nepregledi" class="filterDiv">
		  <td class="myclass">{{tp.datum.substring(0,10)}} {{tp.datum.substring(11,16)}}</td>
		  <td class="myclass">{{tp.trajanje}} minuta</td>
	      <td><input class="btn btn-primary" type='button' value='Detalji'  v-on:click="karton(tp.karton)"/></td>
		  <td><input class="btn btn-primary" value='Odlozite' type='button' v-on:click="odlozite(tp)"/></td>
	  </tr>
	</table>
<div id="modaldark">
		<div id="myForm5">
		<div id="odl-oper-el">
			<label for="newDate">Novi Datum:</label>
			<input id="newDate" type="datetime-local" class="psw" placeholder="Novi datum" v-model="noviDatum">
			<br>
			<div id="btns">
				<input type="button" class="btn-oper-odl" value="Odlozi" v-on:click="odloziOper()"/>
				<input type="button" class="btn-oper-otk" value="Otkazi" v-on:click="zavrsiOdl()"/>
			</div>
		</div>
		</div>

		<div id="myForm4">
			<div id="dostupniLekari">
				<h2 class="text-center">Dostupni lekari</h2>
				<br>
				<table id="dost-lekari-table">
				<tr v-for="l in lekari" style="align: center;">
					<td>{{l.id + " - " + l.ime + " " + l.prezime}}</td>
					<td v-if="dodati.indexOf(l)==-1"><input class="btn-operacija-add" type="button" value="Dodaj" v-on:click="dodajLekara(l)"></td>
					<td v-else><input class="btn-operacija-del" type="button" value="Oduzmi" v-on:click="oduzmiLekara(l)"></td>
				</tr>
				</table>
				<div id="btns">
	     			<input class="btn btn-secondary" value='Otkazi' type='button' v-on:click="otkaziRezervisanje()"/>
	     			<input class="btn btn-primary" value='Zavrsite' type='button' v-on:click="pronadiLekare()"/>
				</div>
			</div>
		</div>

		<div id="myForm" style="display:none;">
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
	   <div id="myForm3">
	   <div id="poruka">
			<p v-if="saljemPregled.datum===ispitanPregled.datum" >Ova sala je slobodna za datum operacije</p>
			<p v-else>Ova sala nije slobodna za datum operacije.Prvi slobodni termin je {{ispitanPregled.datum.substring(0,10)}} {{ispitanPregled.datum.substring(11,16)}}</p>
		  	<p>U nastavku navedite lekare koji ce prisustvovati operaciji.</p>
		   <input class="btn btn-success leftbutton" value='Dalje' type='button' data-toggle="modal" v-on:click="prikaziLekare()"/>
		   <input class="btn btn-danger rightbutton" value='Otkazi' type='button' v-on:click="otkaziRezervisanje()"/>
	   </div>
	   </div>
	   <div id="sa" class="prozor" style="display:none;">
		<table align="left" class="table">
		<tr>
			<td><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput1" placeholder="Naziv sale"></td>
			<td><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput2" placeholder="Broj sale"></td>
			<td><input  type='text' class="form-control"  id='datetimepicker4' required /></td>
			<td><input class="btn btn-success" type='button' value='Pretrazi'  v-on:click="fjaPretrage()"/></td>
			<td><input class="btn btn-success" type='button' value='Prikazi sve'  v-on:click="prikaziSve()"/></td>
		</tr>
		<tr>
		   <th>Broj sale</th>
		   <th>Naziv</th>
		   <th>Kalendar zauzeca</th>
		   <th>Rezervisanje</th>
		</tr>
		<tr v-for="(tp,index) in sale" v-if="tp.klinika==klinika_id" class="filterDivac " >
			<td class="myclass">{{tp.brojSale}}</td>
			<td class="myclass">{{tp.naziv}}</td>
			<td><a href="#" class="menubtn nfitem" v-bind:id="index" v-on:click="prekidac(index)">{{datumPretrage.substring(0,10)}}</a>		
				<div class="menudiv">
					<ul>
						<li v-for="a in tp.pregledi" v-if="a.datum.substring(0,10)===datumPretrage.substring(0,10)" class="nfitem">{{a.datum.substring(11,16)}},trajanje:{{a.trajanje}}min</li>
						<li v-for="a in tp.operacije" v-if="a.datum.substring(0,10)===datumPretrage.substring(0,10)" class="nfitem">{{a.datum.substring(11,16)}},trajanje:{{a.trajanje}}min</li>
					
					</ul>
				</div>	
			</td>
			<td><input class="btn btn-success btn-lg" value='Rezervisi' type='button' v-on:click="rezervisi(tp)"/></td>	
		</tr>
		<tr>
			<td><input class="btn btn-danger otkazibtn" type='button' value='Otkazi'  v-on:click="otkaziZakazivanje()"/></td>
		</tr>	
   </table>
   </div>
	</div>
	</div>
</div>		  
`
	, 
	methods : {
		odlozite(tp){
			this.zaOdlaganje = tp;
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
			document.getElementById("myForm5").style.display = "block";
			document.getElementById("odl-oper-el").style.display = "block";
		},
		odloziOper(){
			if(!moment( $("#newDate").val(), 'YYYY-MM-DDTHH:mm', true).isAfter(moment())){
        		alert("Odaberite datum i vreme u buducnosti!");
        	}
			else if(!moment( $("#newDate").val(), 'YYYY-MM-DDTHH:mm', true).isValid()){
        		alert("Datum nije u ispravnom formatu!\n (YYYY-MM-DD HH:mm)");
        	}
			else{
			axios
			.put('rest/Operacija/odlozi', {"id":this.zaOdlaganje.id, 'novi':this.noviDatum})
			.then(response => {
				axios
				.get('rest/login/getConcreteUser/AdminK')
			    .then((response) => {
					axios
				    .get('rest/Operacija/zahtevi/'+response.data.id,response.data.id)
				    .then(response => {
				    	if(response.data.length==0){
				    		this.operacije=response.data;
				    		this.nemaZahteva();
				    	}else{
				    		this.operacije=response.data;
				    		this.saljemPregled=response.data[0];
					    	this.ispitanPregled=response.data[0];
					    	this.sistemMora(response.data);
				    	}
				    })
					.catch(response => {
						this.$router.push("/");
					});
					axios
				    .get('rest/Operacija/nezahtevi/'+response.data.id,response.data.id)
				    .then(response => {
				    	this.nepregledi=response.data;
						axios
					    .get('rest/sala')
					    .then(response =>(this.sale=response.data));
				    })
					.catch(response => {
						this.$router.push("/");
					});
			    	this.admin=response.data;
					axios
				    .get('rest/login/getKlinika')
				    .then(response =>(this.klinika_id=response.data.id));
			    })
			    .catch(response => {
					this.$router.push("/");
				});
				alert("Uspesno ste odlozili operaciju na dalji datum!");
				this.zavrsiOdl();
			})
			.catch(error => {
				alert("Niste uneli dobar datum! Pokusajte neki drugi");
			});
			}
		},
		zavrsiOdl(){
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
			document.getElementById("myForm5").style.display = "none";
			document.getElementById("odl-oper-el").style.display = "none";
		},prikaziLekare(){
			axios
	    	.get('rest/lekari/dostupni/'+this.ispitanPregled.id)
	    	.then(response => (this.lekari=response.data));
			document.getElementById("myForm4").style.display = "block";
			document.getElementById("dostupniLekari").style.display = "block";
			document.getElementById("myForm").style.display = "none";
			document.getElementById("myForm3").style.display = "none";
			document.getElementById("sa").style.display = "none";
		},
		dodajLekara(l){
			this.dodati.push(l);
		},
		oduzmiLekara(l){
			const index = this.dodati.indexOf(l);
			this.dodati.splice(index, 1);
		},
		pronadiLekare(){
			if (this.dodati.length >= 2){
				document.getElementById("myForm4").style.display = "none";
				document.getElementById("dostupniLekari").style.display = "none";
		    	this.potvrdiRezervisanje();
		    	this.otkaziRezervisanje();
			}
			else{
				alert("Morate uneti barem dva lekara!");
			}
		},
        prikaziSve(){
            var i,li;
            li = document.getElementsByClassName("filterDivac");
            for (i = 0; i < li.length; i++) {
          	  li[i].style.display = "";
            }
        },
		otkaziZakazivanje() {
			document.getElementById("sa").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
        },
		zakazite(ou,traje,pr,a){
        	if(a==0){
        		this.saljemPregled=pr;
        	}
          	var str1=ou.substring(0,10);
        	var str2=" ";
        	var str3=ou.substring(11,16);
        	this.datumPretrage=str1.concat(str2,str3);
			document.getElementById("sa").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
		},
		zakazite2(ou,traje,pr,a){
			if(a==0){
        		this.saljemPregled=pr;
        	}
          	var str1=ou.substring(0,10);
        	var str2=" ";
        	var str3=ou.substring(11,16);
        	this.datumPretrage=str1.concat(str2,str3);
			this.sistemRez();
		},
		sistemRez(){
			axios
        	.put('rest/Operacija/sistem/'+this.saljemPregled.id+"/"+this.klinika_id)
            .then(response =>{
            	this.ispitanPregled=response.data;
    			document.getElementById("myForm3").style.display = "block";
    			document.getElementById("modaldark").style.display = "block";
    			document.getElementById("modaldark").style.opacity="1";
            });
		},
		potvrdiRezervisanje(){
				alert("Sacekajte na rezultate operacije i nemojte nista jos kliknuti!");
				axios
        		.put('rest/Operacija/potvrdi',{"lekari": this.dodati, "operacija":this.ispitanPregled})
            	.then(response =>{
            		axios
            		.get('rest/login/getConcreteUser/AdminK')
            	    .then((response) => {
            			axios
            		    .get('rest/Operacija/zahtevi/'+response.data.id,response.data.id)
            		    .then(response => {
            		    	if(response.data.length==0){
            		    		this.operacije=response.data;
            		    		this.nemaZahteva();
            		    	}else{
            		    		this.operacije=response.data;
            		    		this.saljemPregled=response.data[0];
    					    	this.ispitanPregled=response.data[0];
    					    	this.sistemMora(response.data);
            		    	}
            		    })
            			.catch(response => {
            				this.$router.push("/");
            			});
            			axios
            		    .get('rest/Operacija/nezahtevi/'+response.data.id,response.data.id)
            		    .then(response => {
            		    	this.nepregledi=response.data;
            				axios
            			    .get('rest/sala')
            			    .then(response =>(this.sale=response.data));
            		    })
            			.catch(response => {
            				this.$router.push("/");
            			});
            	    	this.admin=response.data;
            			axios
            		    .get('rest/login/getKlinika')
            		    .then(response =>(this.klinika_id=response.data.id));
            	    })
            	    .catch(response => {
            			this.$router.push("/");
            		});
            		alert("Uspesno ste potvrdili operaciju.");
            		document.getElementById("sa").style.display = "none";
    				document.getElementById("myForm3").style.display = "none";
    				document.getElementById("modaldark").style.display = "none";
    				document.getElementById("modaldark").style.opacity="0";
            	})
            	.catch(error => {
    				alert("Svi lekari su zauzeti.");
    			});
		},
		rezervisi(tp){
        	axios
        	.put('rest/Operacija/ispitaj',{"pregled":this.saljemPregled,"sala":tp})
            .then(response =>{
            	this.ispitanPregled=response.data;
    			document.getElementById("sa").style.display = "none";
    			document.getElementById("myForm3").style.display = "block";
    			document.getElementById("modaldark").style.display = "block";
    			document.getElementById("modaldark").style.opacity="1";
            });
		},
		otkaziRezervisanje(){
			this.ispitanPregled=[];
			document.getElementById("sa").style.display = "block";
			document.getElementById("myForm3").style.display = "none";
			document.getElementById("myForm4").style.display = "none";
			document.getElementById("dostupniLekari").style.display = "none";
		},
		prekidac(a){
			var el=document.getElementById(a);
			var el2=el.nextElementSibling;
			if(el2.style.display==="block"){
				el2.style.display="none";
			}else{
				el2.style.display="block";
			}
		},
        fjaPretrage() {
        	var inp = document.getElementById('datetimepicker4');
        	this.datumPretrage=inp.value;
        	//this.zakazite(this.datumic,this.tt,2,1);
            var lista=[0,1];
            var listaduz=lista.length;
            var n,li,i;
            li = document.getElementsByClassName("filterDivac");
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
  	          }
  	          filter = input.value.toUpperCase();
  	          li = document.getElementsByClassName("filterDivac");
  	
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
        },
		karton(k) {
        	this.trenutniKarton=[];
			this.trenutniKarton=k;
			document.getElementById("sa").style.display = "none";
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
        nemaZahteva(){
        	axios
		    .get('rest/Operacija/nezahtevi/'+this.admin.id,this.admin.id)
		    .then(response => {
		    	this.nepregledi=response.data;
		    	this.saljemPregled=response.data[0];
		    	this.ispitanPregled=response.data[0];
		    })
        },
        sistemMora(data){
        	for (var p of data){
        		sada = new Date();
        		console.log("sada: "+sada.getTime());
        		tada = new Date(p.datum.toString());
        		console.log("tada: "+tada.getTime());
        		razlika = (tada.getTime() - sada.getTime())/(60000*60);
        		console.log("razlika je: "+razlika);
        		if (razlika <= 72){
        			this.sistem.push(p);
        			console.log("id operacije: "+p.id);
        		}
        		console.log("zavrsila se jedna provera");
        	}
        	console.log("trebalo bi da je funkcija gotova");
        }
	},
	mounted(){
		$('#datetimepicker4').datetimepicker();
		axios
		.get('rest/login/getConcreteUser/AdminK')
	    .then((response) => {
			axios
		    .get('rest/Operacija/zahtevi/'+response.data.id,response.data.id)
		    .then(response => {
		    	if(response.data.length==0){
		    		this.operacije=response.data;
		    		this.nemaZahteva();
		    	}else{
		    		this.operacije=response.data;
		    		this.saljemPregled=response.data[0];
			    	this.ispitanPregled=response.data[0];
			    	this.sistemMora(response.data);
		    	}
		    })
			.catch(response => {
				this.$router.push("/");
			});
			axios
		    .get('rest/Operacija/nezahtevi/'+response.data.id,response.data.id)
		    .then(response => {
		    	this.nepregledi=response.data;
				axios
			    .get('rest/sala')
			    .then(response => (this.sale=response.data));
		    })
			.catch(response => {
				this.$router.push("/");
			});
	    	this.admin=response.data;
			axios
		    .get('rest/login/getKlinika')
		    .then(response =>(this.klinika_id=response.data.id));
	    })
	    .catch(response => {
			this.$router.push("/");
		});
	},
});