Vue.component("zahteviPregled", {
	data: function () {
	    return {
	    	admin:null,
	    	admin_id:null,
	    	admin_username:"",
	    	klinika_id:"",
	    	input: {	    		 
                 datum: null,
                 trajanje:null,
                 tipPregleda:"",
                 sala:"",
                 lekar:"",
                 cena:"",
             		},
            dan:[],
            vrijeme: {	    		 
	            pocetak: null,
	            kraj:null
                 },
            nepregledi:[],
            td:null,
            tt:null,
	    	pregledi:[],
		    kalendar:[],
		   	sale:[],
	    	lekari:[],
	    	tipoviPregleda:[],
	    	cenovnik:{
	    		id:null,
	    		stavke:[],
	    		klinika_id:null,
	    	},
	    	preglediOperacije:{
	    		datum:null,
	    		trajanje:null,
	    	},
	    	sviPreglediOperacije:[],
	    	id:null,
	    	trenutniKarton:[],
	    	datumic:"",
	    	slobodna:[],
	    	slobodna1:[],
	    	saljemPregled:null,
	    	sortirano:[],
	    }
	},
	template: ` 
<div class="oneoption">
<div>
<h2 class="text-center">Zahtevi lekara za pregled</h2>
<br>
   <table align="left" class="table klasicna-tabela">
		<tr>
		   <th>Datum i vreme pregleda</th>
		   <th>Trajanje</th>
		   <th>Tip pregleda</th>
		   <th>Lekar</th>
		   <th>Zdravstveni karton</th>
		   <th>Pregled</th>
		</tr>
		<tr v-for="tp in pregledi" class="filterDiv ">
			<td class="myclass">{{tp.datum.substring(0,10)}} {{tp.datum.substring(11,16)}}</td>
			<td class="myclass">{{tp.trajanje}} minuta</td>
			<td class="myclass">{{tp.tip.naziv}}</td>
			<td class="myclass">{{tp.lekar.username}}</td>
			<td><input class="btn btn-primary" type='button' value='Detalji'  v-on:click="karton(tp.karton)"/></td>
			<td><input class="btn btn-primary" value='Zakazite' type='button' v-on:click="zakazite(tp.datum,tp.trajanje,tp,0)"/></td>
		</tr>	
   </table>
   <div id="modaldark">
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
		   <th>Slobodna</th>
		   <th>Rezervisanje<th>
		</tr>
		<tr v-for="(tp,index) in sale" v-if="tp.klinika==klinika_id" class="filterDiv " >
			<td class="myclass">{{tp.brojSale}}</td>
			<td class="myclass">{{tp.naziv}}</td>
			<td v-if="slobodna1[index].substring(0,10)===datumic.substring(0,10) && slobodna1[index].substring(11,16)===datumic.substring(11,16)" ><a href="#" class="menubtn fitem" v-bind:id="index" v-on:click="prekidac(index)" >{{datumic}}</a>
				<div class="menudiv">
					<ul>
						<li v-for="a in tp.pregledi" v-if="a.datum.substring(0,10)===datumic.substring(0,10)" class="nfitem">{{a.datum.substring(11,16)}},trajanje:{{a.trajanje}}min</li>
					</ul>
				</div>
			</td>
			<td v-else><a href="#" class="menubtn nfitem" v-bind:id="index" v-on:click="prekidac(index)">{{datumic}}</a>
				<div class="menudiv">
					<ul>
						<li v-for="a in tp.pregledi" v-if="a.datum.substring(0,10)===datumic.substring(0,10)" class="nfitem">{{a.datum.substring(11,16)}},trajanje:{{a.trajanje}}min</li>
					</ul>
				</div>
			</td>
			<td><input class="btn btn-success btn-lg" type='button' v-model="slobodna[index].substring(0,10)+' '+slobodna[index].substring(11,16)" v-on:click="slobodna(tp.brojSale)"/></td>
			<td><input class="btn btn-success btn-lg" value='Rezervisi' type='button' v-on:click="rezervisi(tp,slobodna[index])"/></td>
	
	</tr>
		<tr>
			<td><input class="btn btn-danger otkazibtn" type='button' value='Otkazi'  v-on:click="otkaziZakazivanje()"/></td>
		</tr>	
   </table>
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
   </div>
</div>
</div>		  
`
	, 
	methods : {
		rezervisi(a,qpr){
			this.saljemPregled.sala=a;
          	/*var str1=ou.substring(0,10);
        	var str2=" ";
        	var str3=ou.substring(11,16);
        	var datumicc=str1.concat(str2,str3);*/
        	this.saljemPregled.datum=qpr;
        	axios
        	.put('rest/Pregled/potvrdi',(this.saljemPregled))
            .then(response =>{
            	axios
    		    .get('rest/Pregled/zahtevi/'+this.admin_id,this.admin_id)
    		    .then(response => (this.pregledi=response.data));
            	alert("Uspesno ste potvrdili pregled.");
            	this.otkaziZakazivanje();
            })
    	    .catch(error => {
    			alert("Svi lekari su zauzeti.");
    		});
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
        prikaziSve(){
            var i,li;
            li = document.getElementsByClassName("filterDiv");
            for (i = 0; i < li.length; i++) {
          	  li[i].style.display = "";
            }
        },
        fjaPretrage() {
        	var inp = document.getElementById('datetimepicker4');
        	this.datumic=inp.value;
        	this.zakazite(this.datumic,this.tt,2,1);
            var lista=[0,1];
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
          },
		zakazite(ou,traje,pr,a) {
        	if(a==0){
        		this.saljemPregled=pr;
        	}
        	var rez=new Date(ou);
        	if(a!=0){
        		rez=new Date(rez.getTime()+120*60000);
        	}
        	var rezKraj = new Date(rez.getTime() + traje*60000);
          	var str1=ou.substring(0,10);
        	var str2=" ";
        	var str3=ou.substring(11,16);
        	this.datumic=str1.concat(str2,str3);
        	var noviPocetak=new Date();
        	var fi,ri,slobodnaa="";
        	this.slobodna1=[];
        	for (fi = 0; fi < this.sale.length; fi++) {
        		    slobodnaa="";
        		    if(this.sale[fi].klinika==this.klinika_id){
        		    if(this.sale[fi].pregledi.length==0){
        		    	this.slobodna1.push(rez.toISOString());
        			}
        		    sviPreglediOperacije=[];
        		    for (ri = 0; ri < this.sale[fi].pregledi.length; ri++) {
        		    	this.preglediOperacije.datum=new Date(this.sale[fi].pregledi[ri].datum);
        		    	this.preglediOperacije.trajanje=this.sale[fi].pregledi[ri].trajanje;
        		    	this.sviPreglediOperacije.push(preglediOperacije);
        		    }
        		    for (ri = 0; ri < this.sale[fi].operacije.length; ri++) {
        		    	this.preglediOperacije.datum=new Date(this.sale[fi].operacije[ri].datum);
        		    	this.preglediOperacije.trajanje=this.sale[fi].operacije[ri].trajanje;
        		    	this.sviPreglediOperacije.push(preglediOperacije);
        		    }
        		    this.sviPreglediOperacije.sort(function(a,b){
        		    	//var dateA = new Date(a.datum), dateB = new Date(b.datum);
        		        return a.datum - b.datum;
        		    });
        			for (ri = 0; ri < this.sviPreglediOperacije.length; ri++) {

        				var trenutniPregled=this.sviPreglediOperacije[ri].datum;
        				var trenutniPregledKraj = new Date(trenutniPregled.getTime() + this.sviPreglediOperacije[ri].trajanje*60000);
        				var prijePregled;
        				if(ri>0){
        					prijePregled=this.sviPreglediOperacije[ri-1].datum;
        					var prijePregledKraj = new Date(prijePregled.getTime() + this.sviPreglediOperacije[ri-1].trajanje*60000);
        				}
        				if(ri==0 && rezKraj<trenutniPregled){
        					slobodnaa="1";
        				}else if(ri==(this.sviPreglediOperacije.length-1) && rez>trenutniPregled){
        					slobodnaa="2";
        				}else if(prijePregledKraj<rez && trenutniPregled>rezKraj){
        					slobodnaa="3";
        				}else if(trenutniPregled>rez){
        					noviPocetak=new Date(trenutniPregled.getTime() - traje*60000);
        					if(ri==0 && noviPocetak.getHours()>=10){
        						slobodnaa="4";
        					}else if(ri==(this.sviPreglediOperacije[ri].length-1)){
        						var noviKraj=new Date(trenutniPregledKraj.getTime() + traje*60000);
        						if(noviKraj.getHours()<23){
        							noviPocetak=trenutniPregledKraj;
        							slobodnaa="5";
        						}else{
        							noviPocetak= new Date(trenutniPregledKraj.getTime() +60*60000*24);
        							noviPocetak.setHours(10);
               					 	noviPocetak.setMinutes(0);
               					 	slobodnaa="6";
        						}
        					}else if((trenutniPregled-prijePregledKraj)>traje*60000){
        						noviPocetak=prijePregledKraj;
        						slobodnaa="7";
        					}
        				}
        				if(slobodnaa!=""){
        					break;
        				}
        			}
        	
        			if(slobodnaa=="1" || slobodnaa=="2" || slobodnaa=="3"){
        				this.slobodna1.push(rez.toISOString());
        			}else{
        				this.slobodna1.push(noviPocetak.toISOString());
        			}
        		    }
	        	}
	        	//alert(this.slobodna1[0]);
	        	if(a==0){
		        	this.td=ou;
		            this.tt=traje;
		        	this.slobodna=this.slobodna1;
        	}
			document.getElementById("sa").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
        },
		otkaziZakazivanje() {
			document.getElementById("sa").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
        },
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
		$('#datetimepicker4').datetimepicker();
		axios
		.get('rest/login/getConcreteUser/AdminK')
	    .then((response) => {
			axios
		    .get('rest/Pregled/zahtevi/'+response.data.id,response.data.id)
		    .then(response => (this.pregledi=response.data))
			.catch(response => {
				this.$router.push("/");
			});
			axios
		    .get('rest/Pregled/nezahtevi/'+response.data.id,response.data.id)
		    .then(response => {this.nepregledi=response.data
				axios
			    .get('rest/sala')
			    .then(response =>{this.sale=response.data
				});
		    })
			.catch(response => {
				this.$router.push("/");
			});
	    	this.admin=response.data;
	    	this.admin_id=response.data.id;
	    	this.admin_username=response.data.username;
	    	//this.klinika_id=response.data.kc_id;
			axios
		    .get('rest/login/getKlinika')
		    .then(response =>(this.klinika_id=response.data.id));
	    })
	    .catch(response => {
			this.$router.push("/");
		});

	},
});