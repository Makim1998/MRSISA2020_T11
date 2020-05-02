Vue.component("lekariPacijent", {
	data: function () {
	    return {
	    	lekari:[],
	    	zaOcenu: "",
	    	ocena: "",
	    	ime: "",
	    	prezime: ""
	    	
	    }
	},
	template: ` 
<div id = "lekariPacijent">
<h2 class="text-center">Lekari</h2>
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
		<tr v-for="l in lekari">
			<td >{{l.ime}}</td>
			<td >{{l.prezime}}</td>
			<td >{{l.username}}</td>
			<td >{{l.prosecnaOcena}}</td>
			<td>
			<button type="button" v-on:click = "popuni(l.username,l.ime,l.prezime)" class="btn btn-primary" data-toggle="modal" data-target="#oceniLekaraModal">
				Oceni lekara
			</button>
			</td>
		</tr>
  </tbody>
</table>

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
		oceni(){
			console.log("ocena");
        	console.log(this.zaOcenu);
        	console.log(this.ocena);

        	console.log("ocena");
        	axios
    	    .post('rest/lekari/ocena?ocena='+this.ocena +'&lekar='+this.zaOcenu )
    	    .then(response => {
    	    	$('#oceniLekaraModal').modal('hide');
            	$('.modal-backdrop').remove();
    	    	console.log("uspeh");
    	    	this.init()});
		}
		
	
	},
	mounted(){
		axios
	    .get('rest/lekari')
	    .then(response => (this.lekari=response.data));
	},
});