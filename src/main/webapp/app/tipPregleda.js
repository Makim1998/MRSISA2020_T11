Vue.component("tipPregleda", {
	data: function () {
	    return {
	    	 input: {
                 pregled: ""
             		},
	    	tipovi:[]
	    }
	},
	template: ` 
<div>
<div>
   <h2>Tipovi pregleda</h2>
   <br>
   <table>
		<tr>
		   <th>ID</th>
		   <th>Tip pregleda</th>
		</tr>
		<tr v-for="tp in tipovi" >
			<td class="myclass">{{tp.id}}</td>
			<td class="myclass">{{tp.naziv}}</option>
			<td><input class='myclass' type='button' value='Uredi' v-on:click="uredi()"/></td>
			<td><input class='myclass' type='button' value='Obrisi' v-on:click="obrisi()"/></td>
		</tr>
		<tr>
			<td colspan="3"><input type="text" class="fotrol" v-model="input.pregled" placeholder="Nazv pregleda"></td>
			<td><input class='myclass' type='button' value='Dodaj' v-on:click="login()"/></td>
		</tr>
   <table>
</div>
</div>		  
`
	, 
	methods : {
		uredi() {
            axios
    		.get('rest/tipPregleda')
    		.then(response => (this.tipovi=response.data));
        },
		obrisi() {
            axios
    		.get('rest/tipPregleda')
    		.then(response => (this.tipovi=response.data));
        },
		dodaj() {
            axios
    		.get('rest/tipPregleda')
    		.then(response => (this.tipovi=response.data));
        }
	},
	mounted(){
		axios
	    .get('rest/tipPregleda')
	    .then(response => (this.tipovi=response.data));
	},
});