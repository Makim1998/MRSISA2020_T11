Vue.component("lekari", {
	data: function () {
	    return {
	    	 input: {	    		 
                 ime: "",
                 prezime: "",
                 username: "",
                 brojOsiguranika: "",
                 password: "",
                 rvod: "",
                 rvdo: ""
             		},
	    	tipovi:[],
	    	id:null,
	    	izmena:"",
	    	klinika_id:null
	    }
	},
	template: ` 
<div class="oneoption">
<div>
	<div class="jumbotron">
	  <h2>Lekari</h2>
	  <p>Pretraga, dodavanje i brisanje.</p> 
	</div>
	<input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput" placeholder="Korisnicko ime">
	<input class="btn btn-success" type='button' value='Pretrazi'  v-on:click="fjaPretrage()"/>
   <table align="left" class="table">
		<tr>
		   <th>ID</th>
		   <th>Korisnicko ime</th>
		   <th>Ime</th>
		   <th>Prezime</th>
		   <th>Broj osiguranika</th>
		   <th>Brisanje</th>
		</tr>
		<tr v-for="tp in tipovi" v-if="tp.kc_id==klinika_id" class="filterDiv ">
			<td class="myclass">{{tp.id}}</td>
			<td class="myclass">{{tp.username}}</td>
			<td class="myclass">{{tp.ime}}</td>
			<td class="myclass">{{tp.prezime}}</td>
			<td class="myclass">{{tp.brojOsiguranika}}</td>
			<td><input class="btn btn-danger btn-lg" value='Obrisi' type='button' v-on:click="obrisi(tp.id)"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input class="btn btn-success" type='button' value='Dodajte novog lekara'  v-on:click="otvori()"/></td>
			<td></td>
			<td><router-link :to="{ name: 'administratorKlinike' }" tag="button" float='right' class="btn btn-primary" >Nazad</router-link></td>
			<td></td>
		</tr>	
   </table>
   <div id="modaldark">
   <div class="form-popup" id="myForm">
    <h4>Novi lekar</h4>
    <input type="text" class="psw" v-model="input.ime" placeholder="Ime" required>
    <input type="text" class="psw" v-model="input.prezime" placeholder="Prezime" required>
    <input type="text" class="psw" v-model="input.brojOsiguranika" placeholder="Broj osiguranika" required>
    <input type="text" class="psw" v-model="input.username" placeholder="Korisnicko ime" required>
    <input type="text" class="psw" v-model="input.password" placeholder="Lozinka" required>
	<p style="margin:0;padding:0;">Radno vreme lekara</p>
	<label for="od">Od:<input type="time" id="od" class="psw" v-model="input.rvod" placeholder="Radno vreme od" required></label>
    <label for="do">Do:<input type="time" id="do" class="psw" v-model="input.rvdo" placeholder="Radno vreme do" required></label>
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
        	.post('rest/lekari/dodaj', {"id": null,
        		"ime":this.input.ime,"prezime":this.input.prezime,
        		"brojOsiguranika":this.input.brojOsiguranika,"password":this.input.password,
        		"username":this.input.username,"radnoVremeOd":this.input.rvod,
        		"radnoVremeDo":this.input.rvdo,"kc_id":this.klinika_id})
			.then(response => {	
				axios
			    .get('rest/lekari')
			    .then(response => (this.tipovi=response.data));
				this.otkazi()
				})
			.catch(error => {
				alert("Nevalidan unos. Pokusajte ponovo.");
			});
        }
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/AdminK')
	    .then((response) => {
	    	console.log(response.data);	
	    })
	    .catch(response => {
			this.$router.push("/");
		});
		axios
	    .get('rest/lekari')
	    .then(response => (this.tipovi=response.data));
		axios
	    .get('rest/login/getKlinika')
	    .then(response =>(this.klinika_id=response.data.id));
	},
});