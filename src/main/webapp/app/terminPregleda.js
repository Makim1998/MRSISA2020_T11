Vue.component("terminPregleda", {
	data: function () {
	    return {
	    	 input: {	    		 
                 datum: "",
                 trajanje:null,
                 tipPregleda: "",
                 sala: "",
                 lekar: "",
                 cena:""
             		},
	    	pregledi:[],
	    	sale:[],
	    	lekari:[],
	    	tipoviPregleda:[],
	    	cenovnik:[],
	    	id:null,
	    }
	},
	template: ` 
<div class="oneoption">
<div>
	<div class="jumbotron">
	  <h2>Slobodni termini za pregelede</h2>
	  <p>Dodavanje i brisanje.</p> 
	</div>
	<input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput" placeholder="Korisnicko ime">
	<input class="btn btn-success" type='button' value='Pretrazi'  v-on:click="fjaPretrage()"/>
   <table align="left" class="table">
		<tr>
		   <th>Datum i vreme pregleda</th>
		   <th>Tip pregleda</th>
		   <th>Trajanje</th>
		   <th>Sala</th>
		   <th>Lekar</th>
		   <th>Cena</th>
		   <th>Brisanje</th>
		</tr>
		<tr v-for="tp in pregledi" class="filterDiv ">
			<td class="myclass">{{tp.datum}}</td>
			<td class="myclass">{{tp.tip}}</td>
			<td class="myclass">{{tp.trajanje}}</td>
			<td class="myclass">{{tp.sala}}</td>
			<td class="myclass">{{tp.lekar}}</td>
			<td class="myclass">{{tp.cena}}</td>
			<td><input class="btn btn-danger btn-lg" value='Obrisi' type='button' v-on:click="obrisi(tp.id)"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input class="btn btn-success" type='button' value='Dodajte novi termin'  v-on:click="otvori()"/></td>
			<td></td>
			<td><router-link :to="{ name: 'administratorKlinike' }" tag="button" float='right' class="btn btn-primary" >Nazad</router-link></td>
			<td></td>
		</tr>	
   </table>
   <div id="modaldark">
   <div class="form-popup" id="myForm">
    <h4>Novi termin</h4>
    <label for="od">Datum:<input type="datetime-local" id="od" class="psw" v-model="input.datum" placeholder="Datum" required></label>
    <label for="tpa">Tip pregleda:<br>
		<select id="tpa" v-model="input.tipPregleda">
			<option v-for="tpa in tipoviPregleda">{{tpa.naziv}}</option>
		</select>
	</label>
	<br>
	<label for="tra">Trajanje:<br>
    <input type="number" id="tra" class="psw" v-model="input.trajanje" placeholder="minute"  min="10" max="60" required>
    </label>
    <br>
    <label for="sal">Sala:<br>
		<select id="sal" v-model="input.sala">
			<option v-for="sal in sale">{{sal.naziv}}</option>
		</select>
	</label>
    <label for="la">Lekar:<br>
		<select id="la" v-model="input.lekar">
			<option v-for="la in lekari">{{la.ime}},{{la.prezime}} ({{la.username}})</option>
		</select>
	</label>
	<br>
	<label for="ca">Cena:<br>
		<select id="ca" v-model="input.cena">
			<option v-for="ca in cenovnik">{{ca.cena}},{{ca.usluga}}</option>
		</select>
	</label>
    </br></br>
    <button type="button" class="btn maal leftbutton" v-on:click="dodaj()">Potvrdi</button>
    <button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
   </div>
   </div>
</div>
</div>		  
`
	, 
	methods : {
		otvori() {
			document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
        },
		otkazi() {
			document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
        },
		obrisi(id) {
            axios
            .delete("rest/terminiPregleda/"+id,id)
            .then(response => this.$router.replace({ name: "administratorKlinike" }));
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
        	axios
        	.post('rest/terminiPregleda/dodaj', {"id":null,"datum":this.input.datum,
        		"trajanje":this.input.ime,"tip":this.input.prezime,"sala":this.input.password,
        		"lekar":this.input.username,"cena":this.input.rvod})
			.then(response => this.$router.replace({ name: "administratorKlinike" }));
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
	    .get('rest/terminPregleda')
	    .then(response => (this.pregledi=response.data));
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
	    .get('rest/Cenovnik')
	    .then(response => (this.cenovnik=response.data));
	},
});