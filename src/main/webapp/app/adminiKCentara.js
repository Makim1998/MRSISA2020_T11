Vue.component("adminiKCentara", {
	data: function(){
		return{
			input:{
				ime: "",
				prezime: "",
				username: "",
                password: "",
                brojOsiguranika: ""
                	},
			tipovi:[],
			id:null,
			inicijalni:null,
			admin_id:null
		}
	},
	template: ` 
		<div class="oneoption_AKC">
		<div>
			<div class="jumbotron">
			  <h2>Administratori klinickih centara</h2> 
			</div>
		   <table align="left" class="table">
				<tr>
				   <th>ID</th>
				   <th>Korisnicko ime</th>
				   <th>Ime</th>
				   <th>Prezime</th>
				   <th>Brisanje</th>
				</tr>
				<tr v-for="tp in tipovi" class="filterDiv ">
					<td class="myclass">{{tp.id}}</td>
					<td class="myclass">{{tp.username}}</td>
					<td class="myclass">{{tp.ime}}</td>
					<td class="myclass">{{tp.prezime}}</td>
					<td><input class="btn btn-danger btn-lg" type='button' value='Obrisi'  v-on:click="obrisi(tp.id)"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn btn-success" type='button' value='Dodajte novog admina klinickog centra'  v-on:click="otvori()"/></td>
				</tr>	
		   </table>
		   <div id="modaldark">
		   <div class="form-popup" id="myForm">
		    <h4>Novi administrator klinickog centra</h4>
		    <input type="text" class="psw" v-model="input.ime" placeholder="Ime" required>
		    <input type="text" class="psw" v-model="input.prezime" placeholder="Prezime" required>
		    <input type="text" class="psw" v-model="input.brojOsiguranika" placeholder="Broj osiguranika" required>
		    <input type="text" class="psw" v-model="input.username" placeholder="Korisnicko ime" required>
		    <input type="password" class="psw" v-model="input.password" placeholder="Lozinka" required>
		    </br></br>
		    <button type="button" class="btn maal leftbutton" v-on:click="dodaj()">Potvrdi</button>
		    <button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
		   </div>
		   <div class="form-popup" id="myForm2">
		      <h6>Da li ste sigurni da zelite da obrisete datog korisnika?</h6>
		      </br></br>
		      <button type="button" class="btn maal leftbutton" v-on:click="del()">Potvrdi</button>
		      <button type="button" class="btn zaal rightbutton" v-on:click="otkazi2()">Otkazi</button>
		   </div>
		   </div>
		</div>
		</div>		  
		`
		, 
		methods : {
			otvori() {
				if (this.inicijalni){
					document.getElementById("myForm").style.display = "block";
					document.getElementById("modaldark").style.display = "block";
					document.getElementById("modaldark").style.opacity="1";
				}
				else{
					alert("Vi nemate pravo da dodajete nove korisnike");
				}
	        },
			otkazi() {
				document.getElementById("myForm").style.display = "none";
				document.getElementById("modaldark").style.display = "none";
				document.getElementById("modaldark").style.opacity="0";
	        },
	        otkazi2() {
				document.getElementById("myForm2").style.display = "none";
				document.getElementById("modaldark").style.display = "none";
				document.getElementById("modaldark").style.opacity="0";
	        },
	        obrisi(id) {
	        	if (admin_id != 1 && admin_id!=2 && admin_id!=3)
	        		alert("Niste ovlasceni da vrsite brisanje administratora!");
	        	else if (id == 1 || id==2 || id==3)
	        		alert("Datog korisnika ne mozete da brisete!");
	        	else{
	        		this.id = id;
					document.getElementById("myForm2").style.display = "block";
					document.getElementById("modaldark").style.display = "block";
					document.getElementById("modaldark").style.opacity="1";
	        	}
	        },
	        proveraPolja(){
	        	if (this.input.ime.trim() == "")
	        		return false;
	        	if (this.input.prezime.trim() == "")
	        		return false;
	        	if (this.input.username.trim() == "")
	        		return false;
	        	if (this.input.password.trim() == "")
	        		return false;
	        	return true;
	        },
			dodaj() {
	        	if (this.proveraPolja()){
	        		axios
	        		.post('rest/adminKC/dodaj', {"id": null,
	        			"ime":this.input.ime,"prezime":this.input.prezime,"password":this.input.password,
	        			"username":this.input.username, "brojOsiguranika":this.input.brojOsiguranika})
	        		.then(response => {	
						axios
				    	.get('rest/adminKC')
				    	.then(response => (this.tipovi=response.data));
						});
	        		this.otkazi();
	        	}
	        	else{
	        		alert("Niste uneli sva polja!");
	        	}
	        },
	        del(){
	        	console.log("Pronadena je operacija za brisanje!");
	        	console.log("Id: "+this.id);
	        	axios
	        	.delete('rest/adminKC/'+this.id)
	        	.then(response => {	
	        		axios
	    		    .get('rest/adminKC')
	    		    .then(response => (this.tipovi=response.data));
	    			axios
	    			.get('rest/adminKC/inicijalni')
	    			.then(response => (this.inicijalni=response.data));
					this.otkazi2();
				})
				.catch(error => {
					alert("Niste ovlasceni da vrsite brisanje administratora!");
				});
	        }
		},
		mounted(){
			axios
		    .get('rest/login/getConcreteUser/AdminKC')
		    .then((response) => {
		    	console.log(response.data);
		    	admin_id = response.data.id;
		    })
		    .catch(response => {
				this.$router.push("/");
			});
			axios
		    .get('rest/adminKC')
		    .then(response => (this.tipovi=response.data));
			axios
			.get('rest/adminKC/inicijalni')
			.then(response => (this.inicijalni=response.data));
		}
});