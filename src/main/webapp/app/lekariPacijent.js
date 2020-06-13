Vue.component("lekariPacijent", {
	props : ['klinika'],
	data: function () {
	    return {
	    	email: "",
	    	lekari:[],
	    	zaOcenu: "",
	    	ocena: "",
	    	ime: "",
	    	prezime: "",
	    	filter: {
	    		ime: "",
	    		prezime: "",
                prosek: ""
	    	},
	    	bulean: false
	    	
	    }
	},
	template: ` 
<div id = "lekariPacijent">
<h2 class="text-center">Lekari</h2>
<div id = "filterLekari">
<form>
  <div class="form-row">
    <div class="col-sm-2 my-1">
      <label for = "prosecnaOcena" class="col-form-label">Prosecna ocena: </label>
		<select id = "prosecnaOcena" v-model = "filter.prosek" class= "form-control">
			<option value = "">Sve</option>
			<option value = "1">1-2</option>
			<option value = "2">2-3</option>
			<option value = "3">3-4</option>
			<option value = "4">4-5</option>
		</select>
    </div>
    <div class="col-sm-3 my-1">
      <label for = "ime" class="col-sm-2 col-form-label">Ime: </label>
		<input id = "ime" type = "text" v-model = "filter.ime" class= "form-control">
    </div>
    <div class="col-sm-3 my-1">
      <label for = "prezime" class="col-sm-2 col-form-label">Prezime: </label>
		<input id = "prezime" type = "text" v-model = "filter.prezime" class= "form-control">
    </div>
    <div class="col-sm-3 my-1">
		<label for = "b" class="col-sm-2 col-form-label"> </label>
      <button id = "b" type="button" class="btn btn-primary" v-on:click="ponistiFilter()">Prikazi sve</button>
    </div>
  </div>
</form>
</div><br>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Ime</th>
      <th scope="col">Prezime</th>
      <th scope="col">email</th>
      <th scope="col">Prosecna ocena</th>
    </tr>
  </thead>
  <tbody>
		<tr v-for="l in filtriraniLekari">
			<td >{{l.ime}}</td>
			<td >{{l.prezime}}</td>
			<td >{{l.username}}</td>
			<td >{{l.prosecnaOcena}}</td>
			<td>
			<button type="button" v-on:click = "popuni(l.username,l.ime,l.prezime)" class="btn btn-primary" data-toggle="modal" data-target="#oceniLekaraModal">
				Oceni lekara
			</button>
			</td>
			<td>
			<button type="button" v-on:click = "skok(l)" class="btn btn-primary">
				Odaberi lekara
			</button>
			</td>
		</tr>
  </tbody>
</table>

<div>
<h5 class="text-center" id = "rezultatiPretrage"></h5>
<h3 class="text-center" v-if="rezultati" ></h3>
</div>

<!-- Modal -->
<div class="modal fade" id="oceniLekaraModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Ocena lekara: {{ime}} {{prezime}}</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <label for = "selekt">Ocena: </label>
        <select id= "selekt" class= "form-group" v-model ="ocena">
        	<option value = "1">1</option>
        	<option value = "2">2</option>
        	<option value = "3">3</option>
        	<option value = "4">4</option>
        	<option value = "5">5</option>
        </select>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
        <button type="button" class="btn btn-primary" v-on:click="oceni()">Posalji ocenu</button>
      </div>
    </div>
  </div>
</div>


</div>	
`
	, 
	methods : {
		popuni: function(email,imee,prezimee){
            console.log(email);
            this.zaOcenu = email;
            this.ime = imee;
            this.prezime = prezimee;
		},
		init(){
			axios
		    .get('rest/lekari')
		    .then(response => (this.lekari=response.data));
			
		},
		ponistiFilter(){
        	console.log("svi lekari");
        	this.filter.ime = "";
        	this.filter.prosek = "";
        	this.filter.prezime = "";
        	axios
		    .get('rest/lekari')
		    .then(response => (this.lekari=response.data));
        },
        skok(lekar){
        	console.log("skok-lekar");
        	this.$emit('skok-lekar',lekar);
        },
		oceni(){
			console.log("ocena");
        	console.log(this.zaOcenu);
        	console.log(this.ocena);

        	console.log("ocena");
        	axios
    	    .post('rest/lekari/ocena?ocena='+this.ocena +'&lekar='+this.zaOcenu  +'&pacijent='+this.email )
    	    .then(response => {
    	    	$('#oceniLekaraModal').modal('hide');
            	$('.modal-backdrop').remove();
    	    	console.log("uspeh");
    	    	this.init()})
        	.catch(function(error){
				if(error.response){
					alert(error.response.data.ime);
				};
        	});
		}
		
	
	},
	computed: {
		filtriraniLekari: function(){
			console.log("prosek");
			console.log(this.filter.prosek);
			console.log(this.filter.ime);
			console.log(this.filter.prezime);
			
			return this.lekari.filter((lekar)=>{
				if(lekar.ime.toLowerCase().match(this.filter.ime.toLowerCase()) == null){
					return false;
				}
				if(lekar.prezime.toLowerCase().match(this.filter.prezime.toLowerCase()) == null){
					return false;
				}
				if(this.filter.prosek == ""){
					return true;
				}
				if(this.filter.prosek == undefined){
					return true;
				}
				return parseFloat(lekar.prosecnaOcena) >= parseFloat(this.filter.prosek) &&
				parseFloat(lekar.prosecnaOcena) <= parseFloat(this.filter.prosek) + 1;
			});
		},
		rezultati: function(){
			console.log(this.filtriraniLekari.length)
			console.log(this.filtriraniLekari.length == 0)
			if(this.filtriraniLekari.length == 0){
				console.log("nema nadjenih");
				$("#rezultatiPretrage").html("Nema rezultata pretrage");
				return true;
			}
			this.bulean = false;
			$("#rezultatiPretrage").html("");
			return false;

		}
	
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/Pacijent')
	    .then((response) => {
	    	console.log(response.data);	
	    	this.email = response.data.username;
	    	console.log(this.email);
	    })
	    .catch(response => {
			this.$router.push("/");
		});
		console.log(this.klinika);
		if (this.klinika == null){
			console.log("Dosao iz navbara")
			axios
		    .get('rest/lekari')
		    .then(response => (this.lekari=response.data));
		}
		else{
			console.log("Dosao pomocu skoka")
			if (this.klinika.lekari == null){
				console.log("Lekari null")
				axios
			    .get('rest/lekari')
			    .then(response => (this.lekari=response.data));
			}
			else{
				console.log("Lekari nisu null");
				this.lekari = this.klinika.lekari;
			}
			
		}
		
	},
});