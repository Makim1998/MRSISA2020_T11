Vue.component("adminiKlinika", {
	data: function(){
		return{
			input:{
				ime: "",
				prezime: "",
				username: "",
                password: "",
                brojOsiguranika: "",
                kc_id: null
                	},
			tipovi:[],
			klinike:[],
			id:null,
			izmena:""
		}
	},
	template: ` 
		<div class="oneoption_AKC">
		<div>
			<div class="jumbotron">
			  <h2>Administratori klinika</h2> 
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
					<td><input class="btn btn-success" type='button' value='Dodajte novog administratora klinike'  v-on:click="otvori()"/></td>
				</tr>	
		   </table>
		   <div id="modaldark">
		   <div class="form-popup" id="myForm">
		    <h4>Novi administrator klinike</h4>
		    <input type="text" class="psw" v-model="input.ime" placeholder="Ime" required>
		    <input type="text" class="psw" v-model="input.prezime" placeholder="Prezime" required>
		    <input type="text" class="psw" v-model="input.brojOsiguranika" placeholder="Broj osiguranika" required>
		    <input type="text" class="psw" v-model="input.username" placeholder="Korisnicko ime" required>
		    <input type="password" class="psw" v-model="input.password" placeholder="Lozinka" required>
		    <label>ID klinike:</label>
		    <select v-model="input.kc_id" required>
		    	<option v-for="k in klinike">{{k}}</option>
		    </select>
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
	        proveraPolja(){
	        	console.log("Stiglo je do provere polja");
	        	if (this.input.ime.trim() == "")
	        		return false;
	        	if (this.input.prezime.trim() == "")
	        		return false;
	        	if (this.input.username.trim() == "")
	        		return false;
	        	if (this.input.password.trim() == "")
	        		return false;
	        	if (this.input.kc_id == "")
	        		return false;
	        	return true;
	        },
			dodaj() {
	        	if (this.proveraPolja()){
	        		console.log("Prosla je provera polja");
	        		axios
	        		.post('rest/adminK/dodaj', {"id": null,
	        			"ime":this.input.ime,"prezime":this.input.prezime,"password":this.input.password,
	        			"username":this.input.username, "kc_id":this.input.kc_id.split(" - ")[0]
	        			, "brojOsiguranika":this.input.brojOsiguranika})
	        		.then(response => {	
						axios
				    	.get('rest/adminK')
				    	.then(response => (this.tipovi=response.data));
						axios
						.get('rest/klinika/adminIds')
						.then(response => (this.klinike=response.data));
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
		    .get('rest/adminK')
		    .then(response => (this.tipovi=response.data));
			axios
			.get('rest/klinika/adminIds')
			.then(response => (this.klinike=response.data));
		}
});