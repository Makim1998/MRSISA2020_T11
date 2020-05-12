Vue.component("adminiKlinika", {
	data: function(){
		return{
			input:{
				ime: "",
				prezime: "",
				username: "",
                password: "",
                kc_id: null
                	},
			tipovi:[],
			klinike:[],
			id:null,
			izmena:""
		}
	},
	template: ` 
		<div class="oneoption">
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
		    <input type="text" class="psw" v-model="input.username" placeholder="Korisnicko ime" required>
		    <input type="text" class="psw" v-model="input.password" placeholder="Lozinka" required>
		    <select v-for="k in klinike" v-model="input.kc_id" required>
		    	<option>{{k}}</option>
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
			dodaj() {
	        	axios
	        	.post('rest/adminK/dodaj', {"id": null,
	        		"ime":this.input.ime,"prezime":this.input.prezime,"password":this.input.password,
	        		"username":this.input.username})
				.then(response => {	
					axios
				    .get('rest/adminK')
				    .then(response => (this.tipovi=response.data));
					});
	        	this.otkazi()
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