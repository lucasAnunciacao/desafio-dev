import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
 
@Injectable()
export class DashboardService {
    constructor(private http: HttpClient) {
 
    }
 
    getCnabsAgreggateByCompany(params: any) {
        const uri = ['/v1/cnab/operations/agreggate/company'].join('/');
        console.log(`Valor uri: ${uri}`)
        return this.http.get(uri, params);
    }
    
    createPosts(body: any) {
    	const uri = ['posts'].join('/');
    	return this.http.post(uri, body);
    }
}