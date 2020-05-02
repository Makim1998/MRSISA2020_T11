Vue.component("klinikePacijent", {
	data: function () {
	    return {
	    	input: {	    		 
                naziv: "",
                opis: "",
                centar: "",
                adresa: ""
            		},
	    	klinike:[],
	    	zaOcenu:"",
	    	ocena:"",
	    	filter: {
	    		naziv: "",
	    		adresa: "",
                prosek: ""
	    	}
            		
	    }
	},
	template: ` 
<div id = "klinikePacijent">
<h2 class="text-center">Klinike</h2>
<div id = "filterKlinike">
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
      <label for = "naziv" class="col-sm-2 col-form-label">Naziv: </label>
		<input id = "naziv" type = "text" v-model = "filter.naziv" class= "form-control">
    </div>
    <div class="col-sm-3 my-1">
      <label for = "adresa" class="col-sm-2 col-form-label">Adresa: </label>
		<input id = "adresa" type = "text" v-model = "filter.adresa" class= "form-control">
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
      <th scope="col">Naziv</th>
      <th scope="col">Adresa</th>
      <th scope="col">Opis</th>
      <th scope="col">Prosecna ocena</th>
    </tr>
  </thead>
  <tbody>
		<tr v-for="k in filtriraneKlinike">
			<td >{{k.naziv}}</td>
			<td >{{k.adresa}}</td>
			<td >{{k.opis}}</td>
			<td >{{k.prosecnaOcena}}</td>
			<td>
			<button type="button" v-on:click = "zaOcenu = k.naziv" class="btn btn-primary" data-toggle="modal" data-target="#oceniKlinikuModal">
				Oceni kliniku
			</button>
			</td>
		</tr>
  </tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="oceniKlinikuModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Ocena klinike: {{zaOcenu}}</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<label for = "selekt">Ocena: </label>
        <select id= "selekt" class= "form-control" v-model ="ocena">
        	<option value = "1">1</option>
        	<option value = "2">2</option>
        	<option value = "3">3</option>
        	<option value = "4">4</option>
        	<option value = "5" selected>5</option>
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
		oceni() {
        	console.log("ocena");
        	console.log(this.zaOcenu);
        	console.log(this.ocena);

        	console.log("ocena");
        	axios
    	    .post('rest/klinika/ocena?ocena='+this.ocena +'&klinika='+this.zaOcenu )
    	    .then(response => {
    	    	$('#oceniKlinikuModal').modal('hide');
            	$('.modal-backdrop').remove();
    	    	console.log("uspeh");
    	    	this.init()});
    	    
        	
        },
        ponistiFilter(){
        	console.log("klinike");
        	this.filter.grad = "";
        	this.filter.prosek = "";
        	this.filter.naziv = "";
        },
		init(){
        	console.log("klinike");
    		axios
    	    .get('rest/klinika')
    	    .then(response => (this.klinike=response.data));
		}
	},
	computed: {
		filtriraneKlinike: function(){
			console.log("prosek");
			console.log(this.filter.prosek);
			console.log(this.filter.naziv);
			console.log(this.filter.adresa);
			
			return this.klinike.filter((klinika)=>{
				console.log("hhha")
				console.log(klinika.adresa.toLowerCase());
				console.log(this.filter.adresa.toLowerCase());
				console.log(klinika.adresa.toLowerCase().match(this.filter.adresa.toLowerCase()) == null)
				console.log("hhha")
				if(this.filter.prosek == undefined){
					return true;
				}

				if(klinika.adresa.toLowerCase().match(this.filter.adresa.toLowerCase()) == null){
					return false;
				}
				if(klinika.naziv.toLowerCase().match(this.filter.naziv.toLowerCase()) == null){
					return false;
				}
				if(this.filter.prosek == ""){
					return true;
				}
				if(this.filter.prosek == undefined){
					return true;
				}
				return parseFloat(klinika.prosecnaOcena) >= parseFloat(this.filter.prosek) &&
				parseFloat(klinika.prosecnaOcena) <= parseFloat(this.filter.prosek) + 1;
			});
		}
	
	},
	mounted(){
		console.log("klinike");
		axios
	    .get('rest/klinika')
	    .then(response => (this.klinike=response.data));
	},	
});