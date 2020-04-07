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
			<td><input class='iUre' type='button' value='Uredi' v-on:click="uredi(tp.id)"/></td>
			<td><input class= 'dsss' type='button' value='Obrisi' v-on:click="obrisi(tp.id)"/></td>
		</tr>
		<tr>
			<td colspan="3"><input type="text" class="fotrol" v-model="input.pregled" placeholder="Nazv pregleda"></td>
			<td><input class='myclass' type='button' value='Dodaj' v-on:click="dodaj()"/></td>
		</tr>
   </table>
</div>
</div>		  
`
	, 
	methods : {
		uredi() {
			alert("uredi")
			var div = document.getElementById('div_id');
			div.style.display = 'block';
           // axios
    		//.get('rest/tipPregleda')
    		//.then(response => (this.tipovi=response.data));
        },
		obrisi(id) {
            axios
            .delete("rest/tipPregleda/"+id,id)
            .then(response => this.$router.replace({ name: "homepage" }));
        },
		dodaj() {
        	axios
        	.post('rest/tipPregleda/dodaj', {"id": null, "naziv":this.input.pregled})
			.then(response => this.$router.replace({ name: "homepage" }));
        }
	},
	mounted(){
		axios
	    .get('rest/tipPregleda')
	    .then(response => (this.tipovi=response.data));
	},
});