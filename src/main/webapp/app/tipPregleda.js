Vue.component("tipPregleda", {
	data: function () {
	    return {
	    	 input: {	    		 
                 pregled: ""
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
	  <h2>Tipovi pregleda</h2>
	  <p>Pretraga, dodavanje, izmena i brisanje.</p> 
	</div>
	<input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput" placeholder="Naziv pregleda">
	<input class="btn btn-success" type='button' value='Pretrazi'  v-on:click="fjaPretrage()"/>
   <table align="left" class="table">
		<tr>
		   <th>ID</th>
		   <th>Tip pregleda</th>
		   <th>Izmena</th>
		   <th>Brisanje</th>
		</tr>
		<tr v-for="tp in tipovi"  v-if="tp.klinika==klinika_id" class="filterDiv " >
			<td class="myclass">{{tp.id}}</td>
			<td class="myclass">{{tp.naziv}}</option>
			<td><input class="btn btn-warning btn-lg" value='Izmeni' type='button'  v-on:click="uredi(tp.id,tp.naziv)"/></td>
			<td><input class="btn btn-danger btn-lg" value='Obrisi' type='button' v-on:click="obrisi(tp.id)"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="text" class="fotrol" v-model="input.pregled" placeholder="Naziv pregleda"></td>
			<td><input class="btn btn-success" type='button' value='Dodavanje'  v-on:click="dodaj()"/></td>
		</tr>	
   </table>
   <div id="modaldark">
   <div class="form-popup" id="myForm">
    <h6>ID:{{this.id}}</h6>
    <input type="text" class="psw" v-model="izmena" placeholder="Naziv pregleda">
    </br></br>
	<button type="button" class="btn maal leftbutton" v-on:click="izmeni()">Potvrdi</button>
	<button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
   </div>
   </div>
</div>
</div>		  
`
	, 
	methods : {
		uredi(id,izmena) {
			this.izmena=izmena;
			this.id=id;
			document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
        },
		izmeni() {     
        	axios
        	.put('rest/tipPregleda/izmeni', {"id":this.id, "naziv":this.izmena,"klinika":this.klinika_id})
			.then(response => {
				axios
			    .get('rest/tipPregleda')
			    .then(response => (this.tipovi=response.data));
			})
			.catch(error => {
				alert("Nevalidan unos. Pokusajte ponovo.");
			});
			document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";

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
		otkazi() {
			document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
        },
		obrisi(id) {
            axios
            .delete("rest/tipPregleda/"+id,id)
            .then(response =>{
				axios
			    .get('rest/tipPregleda')
			    .then(response => (this.tipovi=response.data));
			})
			.catch(error => {
				alert("Ne moze se obrisati.Pregled ovog tipa je vec zakazan.");
			});
        },
		dodaj() {
        	axios
        	.post('rest/tipPregleda/dodaj', {"id": null, "naziv":this.input.pregled,"klinika":this.klinika_id})
			.then(response =>{
				axios
			    .get('rest/tipPregleda')
			    .then(response => (this.tipovi=response.data));
			})
			.catch(error => {
				alert("Nevalidan unos. Pokusajte ponovo.");
			});
        	this.input.pregled="";
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
	    .get('rest/tipPregleda')
	    .then(response => (this.tipovi=response.data));
		axios
	    .get('rest/login/getKlinika')
	    .then(response =>(this.klinika_id=response.data.id));
		
	},
});