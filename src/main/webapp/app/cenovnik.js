Vue.component('cenovnik', {
	data: function () {
	    return {
	    	 input: {	    		 
                 naziv: "",
                 cena:null
             		},
	    	cenovnik:{
	    		id:null,
	    		stavke:[],
	    		klinika_id:null,
	    	},
	    	kc_id:null,
	    	id:null,
	    	naziv:"",
	    	cena:null
	    }
	},
	template: ` 
<div class="oneoption">
<div>
<h2 class="text-center">Cenovnik klinike</h2>
  <div class="form-row">
    <div class="col-sm-2 my-1">
     <input type="text" style="margin-left:10px;margin-top:5px;" class="fotrol" id="myInput" placeholder="Naziv usluge">
    </div>
    <div class="col-sm-2 my-1">
      	<input class="btn btn-primary" type='button' value='Pretrazi'  v-on:click="fjaPretrage()"/>
    </div>
  </div>
<br>
   <table align="left" class="table klasicna-tabela">
		<tr>
		   <th>ID</th>
		   <th>Cena</th>
		   <th>Usluga</th>
		   <th>Izmena</th>
		   <th>Brisanje</th>
		</tr>
		<tr v-for="tp in cenovnik.stavke" class="filterDiv " >
			<td class="myclass">{{tp.id}}</td>
			<td class="myclass">{{tp.cena}}</td>
			<td class="myclass">{{tp.usluga}}</td>
			<td><input class="btn btn-primary btn-lg" value='Izmeni' type='button'  data-toggle="modal" data-target="#izmeni" v-on:click="uredi(tp.id,tp.cena,tp.usluga)"/></td>
			<td><input class="btn btn-primary btn-lg" value='Obrisi' type='button' v-on:click="obrisi(tp.id)"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="number" min="500" max="100000" class="fotrol" v-model="input.cena" placeholder="Cena"></td>
			<td><input type="text" class="fotrol" v-model="input.naziv" placeholder="Naziv usluge"></td>
			<td><input class="btn btn-primary" type='button' value='Dodaj stavku'  v-on:click="dodaj()"/></td>
		</tr>	
   </table>
  <!-- Modal -->
<div class="modal fade" id="izmeni" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" >Izmena ID: {{this.id}}</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form>
			<div class="form-group">
		      	<label for = "nazivusluge">Naziv usluge: </label>
				<input id="nazivusluge" type="text" class="psw" v-model="naziv">
		    </div>
		    <div class="form-group">
		      	<label for = "cena">Cena: </label>
		        <input  id="cena" type="number" min="500" max="100000" class="psw" v-model="cena">
		    </div>

		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
        <button type="button" class="btn btn-primary" data-dissmiss="modal" v-on:click="izmeni()">Potvrdi</button>
      </div>
    </div>
  </div>
</div>
   <!---div id="modaldark">
   <div class="form-popup" id="myForm">
    <h6>Izmena ID:{{this.id}}</h6>
    <input type="text" class="psw" v-model="naziv" placeholder="Naziv usluge">
    <input type="number" min="500" max="100000" class="psw" v-model="cena" placeholder="Cena">
    </br></br>
    <button type="button" class="btn maal leftbutton" v-on:click="izmeni()">Potvrdi</button>
    <button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
   </div>
   </div--->
</div>
</div>		  
`
	, 
	methods : {
		uredi(id,cena,naziv) {
			this.id=id;
			this.cena=cena;
			this.naziv=naziv;
			/*document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";*/
        },
		izmeni() {      
        	axios
        	.put('rest/cenovnik/izmeniStavku', {"c_id":this.cenovnik.id,"id":this.id,"cena":this.cena, "usluga":this.naziv})
			.then(response =>{
				axios
			    .get('rest/cenovnik/'+this.kc_id,this.kc_id)
			    .then(response =>{
			    	this.cenovnik.id=response.data.id;
			    	this.cenovnik.stavke = response.data.stavke;
			    	this.cenovnik.klinika_id = response.data.klinikaID;
			    })
				.catch(error => {
					alert("Nevalidan unos. Pokusajte ponovo.");
				});
			});
        	$('#izmeni').modal('hide');
        	$('.modal-backdrop').remove();
			/*document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";*/

        },
        fjaPretrage() {
            var input, filter, ul, li, a, i, txtValue;
            input = document.getElementById('myInput');
            filter = input.value.toUpperCase();
            li = document.getElementsByClassName("filterDiv");

            for (i = 0; i < li.length; i++) {
              a = li[i].getElementsByTagName("td")[2];
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
            .delete("rest/cenovnik/obrisiStavku/"+id,id)
            .then(response =>{
				axios
			    .get('rest/cenovnik/'+this.kc_id,this.kc_id)
			    .then(response =>{
			    	this.cenovnik.id=response.data.id;
			    	this.cenovnik.stavke = response.data.stavke;
			    	this.cenovnik.klinika_id = response.data.klinikaID;
			    });
			})
			.catch(error => {
				alert("Stavka cenovnika se nalazi u zakazanom pregledu.");
			});
        },
		dodaj() {
        	axios
        	.post('rest/cenovnik/dodajStavku', {"c_id":this.cenovnik.id,"id:": null,"cena":this.input.cena, "usluga":this.input.naziv})
			.then(response => {
				axios
			    .get('rest/cenovnik/'+this.kc_id,this.kc_id)
			    .then(response =>{
			    	this.cenovnik.id=response.data.id;
			    	this.cenovnik.stavke = response.data.stavke;
			    	this.cenovnik.klinika_id = response.data.klinikaID;
			    });
			})
			.catch(error => {
				alert("Nevalidan unos. Pokusajte ponovo.");
			});
	        this.input.cena=null;
	        this.input.naziv="";
        }
	},
	mounted(){
		axios
	    .get('rest/login/getKlinika')
	    .then((response) => {
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
	}
});