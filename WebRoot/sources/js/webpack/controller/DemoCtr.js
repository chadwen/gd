
//DemoCtr.js
import jq from '../unit/JQueryVendor';
class Demo{
	constructor() {
        this.num = 5;
        
        
        
        
        this.initEvent();
    }
	initEvent(){
		let self = this;
		
		self._demo();
		
		self._put();
		
		$('#btn').on('click',function(){
			console.log(self.num);
			console.log('here is the testjq function');
		});
	}
//--------------------------------------------------------------------------------------------------
	
	_demo(){
		let self = this;//avoid unnecessary error from 'this' key word
		console.log('rmrmrm');
		console.log(self.num);
	}
	_put(){
		let self = this;
		let greet = document.createElement('div');
		greet.textContent = "aaaaaaaaaaaaaaaaaaaaaaa Hi there and greetings!";
		document.getElementById('root').appendChild(greet);
	}
	
}
export default Demo;