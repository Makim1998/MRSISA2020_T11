Vue.component("zakazivanjeLekar", {
	data: function () {
	    return {
	    	 input: {	    		 
                 datum: null,
                 trajanje:null,
                 tipPregleda:"",
                 sala:"",
                 lekar:"",
                 cena:""
             		},
	    	tipovi:[],
	    	pacijent:[],
	    	karton:[],
	    	id:null,
	    	izmena:"",
	    	klinika_id:null,
	    	lekar_username:null,
	    	lekar_id:null,
	    	cenovnik:{
	    		id:null,
	    		stavke:[],
	    		klinika_id:null,
	    	},
	    	pregledi:[],
	    }
	},
	template: ` 
<div class="oneoption">
<div>
	<div class="jumbotron" style="width:105%;">
	  <h2>Zakazivanje pregleda i operacija</h2> 
	</div>
   <table align="left" class="table">
   		<tr>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput1" placeholder="Ime"></th>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput2" placeholder="Prezime"></th>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput3" placeholder="Broj osiguranika"></th>
		   <th><input class="btn btn-success"  type='button' value='Pretrazi'  v-on:click="fjaPretrage()"/></th>
		   <th ><input class="btn btn-success"  type='button' value='Prikazi sve'  v-on:click="prikaziSve()"/></th>
		</tr>
		<tr>
		   <th>Ime</th>
		   <th>Prezime</th>
		   <th>Broj osiguranika</th>
		   <th colspan="2" style="text-align:center;" >Zakazivanje</th>
		</tr>
		<tr v-for="tp in tipovi" v-if="tp.kc_id==klinika_id" class="filterDiv ">
			<td class="myclass">{{tp.ime}}</td>
			<td class="myclass">{{tp.prezime}}</td>
			<td class="myclass">{{tp.brojOsiguranika}}</td>
			<td colspan="2" style="text-align:center;"><input class="btn btn-success" style="margin-top:10px;" type='button' value='Zakazite'  v-on:click="zakazi()"/></td>
		</tr>
   </table>
   
    <div id="modaldark">
   <div class="form-popup" id="myForm">
    <h4>Novi termin</h4>
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
    <button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
   </div>
   </div>
</div>
</div>		  
`
	, 
	methods : {
		zakazi(){
			document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
		},
		prikaziPacijenta(pacijent,karton){
			alert("rs");
			axios
		    .get('rest/Pregled/svi')
		    .then(response => {
				this.pacijent=pacijent;
				this.karton=karton;
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
		    			}
		    		}
		    		if(obj.lekar.username==this.lekar_username && obj.karton!=null && obj.dijagnoza!=null){
		    			if(obj.karton.id==this.karton.id){
		    				this.karton=obj.karton;
		    				document.getElementById("pKartona").style.display = "block";
		    			}
		    		}
		    	}
		    });	
		},
		otkazi() {
			document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
        },
		zakaziOperaciju() {
        	axios
        	.post('rest/Operacija/zakazi', {"id":null,"datum":this.input.datum,
        		"trajanje":this.input.trajanje,"tip":this.input.tipPregleda,"cena":this.input.cena,
        		"sala":this.input.sala,"lekar":this.input.lekar,})
            .then(response =>{
            	alert("Uspesno ste zakazali operaciju");
            })
			.catch(error => {
				alert("Nevalidan unos. Pokusajte ponovo.");
			});
        },
		zakaziPregled() {
        	axios
        	.post('rest/Pregled/dodaj', {"id":null,"datum":this.input.datum,
        		"trajanje":this.input.trajanje,"tip":null,"cena":this.input.cena,
        		"sala":null,"lekar":this.input.lekar,})
            .then(response =>{
            	alert("Uspesno ste zakazali pregled");
            	this.otkazi()
            })
			.catch(error => {
				alert("Nevalidan unos. Pokusajte ponovo.");
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
	    	this.lekar_id=response.data.id;
	    	this.lekar_username=response.data.username;
	    	this.klinika_id=response.data.kc_id;
			axios
			.get('rest/pacijent/pregledani/'+this.lekar_id,this.lekar_id)
		    .then(response => (this.tipovi=response.data));
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