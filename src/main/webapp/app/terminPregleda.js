Vue.component("terminPregleda", {
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
	    }
	},
	template: ` 
<div class="oneoption">
<div>
	<h2 class="text-center">Slobodni termini za preglede</h2>
  <div class="form-row">
    <div class="col-sm-2 my-1">
     <input type="text" style="margin-left:10px;margin-top:5px;" class="fotrol" id="myInput" placeholder="Tip pregleda">
    </div>
    <div class="col-sm-2 my-1">
      	<input class="btn btn-primary" type='button' value='Pretrazi'  v-on:click="fjaPretrage()"/>
    </div>
  </div>
<br>
   <table align="left" class="table klasicna-tabela">
		<tr>
		   <th>Datum i vreme pregleda</th>
		   <th>Tip pregleda</th>
		   <th>Trajanje</th>
		   <th>Sala</th>
		   <th>Lekar</th>
		   <th>Cena</th>
		   <th>Brisanje</th>
		</tr>
		<tr v-for="tp in pregledi" v-if="tp.sala.klinika==cenovnik.klinika_id" class="filterDiv ">
			<td class="myclass">{{tp.datum.substring(0,10)}} {{tp.datum.substring(11,16)}}</td>
			<td class="myclass">{{tp.tip.naziv}}</td>
			<td class="myclass">{{tp.trajanje}}</td>
			<td class="myclass">{{tp.sala.naziv}}</td>
			<td class="myclass">{{tp.lekar.username}}</td>
			<td class="myclass">{{tp.cena.cena}}</td>
			<td><input class="btn btn-primary btn-lg" value='Obrisi' type='button' v-on:click="obrisi(tp.id)"/></td>
		</tr>
		<tr>
			<td><input class="btn btn-primary" type='button' value='Dodajte novi termin' data-toggle="modal" data-target="#novitermin" v-on:click="otvori()"/></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>	
   </table>
   <!-- Modal -->
<div class="modal fade" id="novitermin" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" >Novi termin za pregled</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form>
			<div class="form-group">
		      	<label for = "od">Datum: </label>
				<input type="datetime-local" id="od" class="psw" v-model="input.datum" required>
		    </div>
		    <div class="form-group">
		      	 <label for="tpa">Tip pregleda:</label>
					<select id="tpa" v-model="input.tipPregleda">
						<option v-for="tpa in tipoviPregleda" v-if="tpa.klinika==cenovnik.klinika_id" >{{tpa.naziv}}-ID:{{tpa.id}}</option>
					</select>
		    </div>
		    <div class="form-group">
		      	<label for = "tra">Trajanje: </label>
				<input type="number" id="tra" class="psw" v-model="input.trajanje" min="10" max="60" required>
		    </div>
		    <div class="form-group">
		      	<label for="sal">Sala:</label>
					<select id="sal" v-model="input.sala">
						<option v-for="sal in sale" v-if="sal.klinika==cenovnik.klinika_id" >{{sal.naziv}}-ID:{{sal.klinika}},{{sal.brojSale}}</option>
					</select>
		    </div>
		    <div class="form-group">
				<label for="la">Lekar:</label>
					<select id="la" v-model="input.lekar">
						<option v-for="la in lekari" v-if="la.kc_id==cenovnik.klinika_id">{{la.ime}}-{{la.prezime}}-ID:{{la.id}}</option>
					</select>
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
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
        <button type="button" class="btn btn-primary" data-dissmiss="modal" v-on:click="dodaj()">Potvrdi</button>
      </div>
    </div>
  </div>
</div>
   <!----div id="modaldark">
   <div class="form-popup" id="myForm">
    <h4>Novi termin</h4>
    <label for="od">Datum:<input type="datetime-local" id="od" class="psw" v-model="input.datum" placeholder="Datum" required></label>
    <label for="tpa">Tip pregleda:<br>
		<select id="tpa" v-model="input.tipPregleda">
			<option v-for="tpa in tipoviPregleda" v-if="tpa.klinika==cenovnik.klinika_id" >{{tpa.naziv}}-ID:{{tpa.id}}</option>
		</select>
	</label>
	<br>
	<label for="tra">Trajanje:<br>
    <input type="number" id="tra" class="psw" v-model="input.trajanje" placeholder="minute"  min="10" max="60" required>
    </label>
    <br>
    <label for="sal">Sala:<br>
		<select id="sal" v-model="input.sala">
			<option v-for="sal in sale" v-if="sal.klinika==cenovnik.klinika_id" >{{sal.naziv}}-ID:{{sal.klinika}},{{sal.brojSale}}</option>
		</select>
	</label>
    <label for="la">Lekar:<br>
		<select id="la" v-model="input.lekar">
			<option v-for="la in lekari" v-if="la.kc_id==cenovnik.klinika_id">{{la.ime}}-{{la.prezime}}-ID:{{la.id}}</option>
		</select>
	</label>
	<br>
	<label for="ca">Cena:<br>
		<select id="ca" v-model="input.cena">
			<option v-for="ca in cenovnik.stavke">{{ca.usluga}}-{{ca.cena}}DIN-ID:{{ca.id}}</option>
		</select>
	</label>
    </br></br>
    <button type="button" class="btn maal leftbutton" v-on:click="dodaj()">Potvrdi</button>
    <button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
   </div>
   </div----->
</div>
</div>		  
`
	, 
	methods : {
		otvori() {
			/*document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";*/
        },
		otkazi() {
			/*document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";*/
        },
		obrisi(id) {
            axios
            .delete("rest/Pregled/"+id,id)
            .then(response =>{
            	axios
            	.get('rest/Pregled/slobodni')
            	.then(response => (this.pregledi=response.data));
            });
        },
        fjaPretrage() {
          var input, filter, ul, li, a, i, txtValue;
          input = document.getElementById('myInput');
          filter = input.value.toUpperCase();
          li = document.getElementsByClassName("filterDiv");

          for (i = 0; i < li.length; i++) {
            a = li[i].getElementsByTagName("td")[1];
            txtValue = a.textContent || a.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
              li[i].style.display = "";
            } else {
              li[i].style.display = "none";
            }
          }
        },
		dodaj() {
			if(!moment( $("#od").val(), 'YYYY-MM-DDTHH:mm', true).isAfter(moment())){
        		alert("Odaberite datum i vreme u buducnosti!");
        		return;
        	}
			if(!moment( $("#od").val(), 'YYYY-MM-DDTHH:mm', true).isValid()){
        		alert("Datum nije u ispravnom formatu!\n (YYYY-MM-DD HH:mm)");
        		return;
        	}
        	axios
        	.post('rest/Pregled/dodaj', {"id":null,"datum":this.input.datum,
        		"trajanje":this.input.trajanje,"tip":this.input.tipPregleda,"cena":this.input.cena,
        		"sala":this.input.sala,"lekar":this.input.lekar,})
            .then(response =>{
            	axios
            	.get('rest/Pregled/slobodni')
            	.then(response => (this.pregledi=response.data));
            	$('#novitermin').modal('hide');
            	$('.modal-backdrop').remove();
            })
			.catch(error => {
 				if(error.response.data==="Lekar nije specijalizovan za dati tip pregleda!" || error.response.data==="Nemate pravo pristupa" ||
 						error.response.data==="Lekar nije specijalizovan za dati tip pregleda!"){
 					alert(error.response.data);
 				}else{
 					alert("Greska");
 				}
			});
        }
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/AdminK')
	    .then((response) => {
	    	this.id=response.data.kc_id;
	    	console.log(response.data);	
	    })
	    .catch(response => {
			this.$router.push("/");
		});
		axios
	    .get('rest/Pregled/slobodni')
	    .then(response => {
	    	this.pregledi=response.data;
	    });
		axios
	    .get('rest/lekari')
	    .then(response => (this.lekari=response.data));
		axios
	    .get('rest/sala')
	    .then(response => (this.sale=response.data));
		axios
	    .get('rest/tipPregleda')
	    .then(response => (this.tipoviPregleda=response.data));
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