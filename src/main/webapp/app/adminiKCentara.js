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
			inicijalni:null
		}
	},
	template: ` 
		<div class="oneoption">
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
				</tr>
				<tr v-for="tp in tipovi" class="filterDiv ">
					<td class="myclass">{{tp.id}}</td>
					<td class="myclass">{{tp.username}}</td>
					<td class="myclass">{{tp.ime}}</td>
					<td class="myclass">{{tp.prezime}}</td>
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
	        }
		},
		mounted(){
			axios
		    .get('rest/login/getConcreteUser/AdminKC')
		    .then((response) => {
		    	console.log(response.data);	
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