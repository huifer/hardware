import axios from "axios";
import qs from "query-string";


export function HardwareTypePage(request, page) {
  return axios.get('http://localhost:9011/hardware_type/page', {
    params: Object.assign(request, page), paramsSerializer: params => {
      const keys = Object.keys(params);
      keys.forEach(key => {
        if (!params[key]) {
          delete params[key];
        }
      });


      return qs.stringify(params, {indices: false})
    }
  });
}


export function HardwareTypeCreate(reuqest){
  return axios.post('http://localhost:9011/hardware_type/save',reuqest);
}export function HardwareTypeUpdate(reuqest){
  return axios.post('http://localhost:9011/hardware_type/update',reuqest);
}
export function HardwareTypeById(id){
  return axios.get('http://localhost:9011/hardware_type/byId/' + id);
}