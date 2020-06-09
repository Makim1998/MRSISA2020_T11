Vue.component("zakazivanjeLekar", {
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
                 lekari:[]
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
<div class="oneoptionlekar">
<div>
	<h2 class="text-center">Zakazivanje pregleda i operacija</h2>
<br>
   <table align="left" class="table klasicna-tabela">
   		<tr>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput1" placeholder="Ime"></th>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput2" placeholder="Prezime"></th>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput3" placeholder="Broj osiguranika"></th>
		   <th><input class="btn btn-primary"  type='button' value='Pretrazi'  v-on:click="fjaPretrage()"/></th>
		   <th ><input class="btn btn-primary"  type='button' value='Prikazi sve'  v-on:click="prikaziSve()"/></th>
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
			<td colspan="2" style="text-align:center;"><input class="btn btn-primary" style="margin-top:10px;" type='button' value='Zakazite' data-toggle="modal" data-target="#novipregled" v-on:click="zakazi(tp.karton)"/></td>
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
    <!----div id="modaldark">
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
   </div---->
</div>
</div>		  
`
	, 
	methods : {
		zakazi(a){
			this.input.karton=a;
			/*document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";*/
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
        	this.input.lekari=[];
        	this.input.lekari.push(this.input.lekar);
        	axios
        	.post('rest/Operacija/dodaj', {"id":null,"datum":this.input.datum,
        		"trajanje":this.input.trajanje,"cena":this.input.cena,"karton":this.input.karton,
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