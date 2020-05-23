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
	<h2 class="text-center">Lekari</h2>
  <div class="form-row">
    <div class="col-sm-2 my-1">
     <input type="text" style="margin-left:10px;margin-top:5px;" class="fotrol" id="myInput" placeholder="Korisnicko ime">
    </div>
    <div class="col-sm-2 my-1">
      	<input class="btn btn-primary" type='button' value='Pretrazi'  v-on:click="fjaPretrage()"/>
    </div>
  </div>
<br>
	
   <table align="left" class="table klasicna-tabela">
		<tr>
		   <th>ID</th>
		   <th>Korisnicko ime</th>
		   <th>Ime</th>
		   <th>Prezime</th>
		   <th>Prosecna ocena</th>
		   <th>Broj osiguranika</th>
		   <th>Brisanje</th>
		</tr>
		<tr v-for="tp in tipovi" v-if="tp.kc_id==klinika_id" class="filterDiv ">
			<td class="myclass">{{tp.id}}</td>
			<td class="myclass">{{tp.username}}</td>
			<td class="myclass">{{tp.ime}}</td>
			<td class="myclass">{{tp.prezime}}</td>
			<td class="myclass">{{tp.prosecnaOcena}}</td>
			<td class="myclass">{{tp.brojOsiguranika}}</td>
			<td><input class="btn btn-primary btn-lg" value='Obrisi' type='button' v-on:click="obrisi(tp.id)"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input class="btn btn-primary" type='button' value='Dodajte novog lekara'  data-toggle="modal" data-target="#novilekar"/></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>	
   </table>
<!-- Modal -->
<div class="modal fade" id="novilekar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" >Novi lekar</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form>
			<div class="form-group">
		      	<label for = "ime">Ime: </label>
		      	<input id="ime" type="text" class="psw" v-model="input.ime" required>
		    </div>
		    <div class="form-group">
		      	<label for = "prezime">Prezime: </label>
				<input id="prezime" type="text" class="psw" v-model="input.prezime" required>
		    </div>
		    <div class="form-group">
		      	<label for = "brojos">Broj osiguranika: </label>
				<input id="brojos" type="text" class="psw" v-model="input.brojOsiguranika"  required>
		    </div>
		    <div class="form-group">
		      	<label for = "korime">Korisnicko ime: </label>
				<input id="korime" type="text" class="psw" v-model="input.username" required>
		    </div>
		    <div class="form-group">
		      	<label for = "lozinka">Lozinka: </label>
				<input id="lozinka" type="text" class="psw" v-model="input.password" required>
		    </div>
		    <div class="form-group">
		    	<label>Radno vreme lekara:</label><br>
		    	<label for="od">Od:<input type="time" id="od" class="psw" v-model="input.rvod" placeholder="Radno vreme od" required></label>
				<label for="do">Do:<input type="time" id="do" class="psw" v-model="input.rvdo" placeholder="Radno vreme do" required></label>
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
   <!---div id="modaldark">
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
   </div---->
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
				$('#novilekar').modal('hide');
            	$('.modal-backdrop').remove();
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