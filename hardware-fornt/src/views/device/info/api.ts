import axios from "axios";
import qs from "query-string";


export function HardwareInfoPage(request, page) {
  return axios.get('http://localhost:9011/hardware_info/page', {
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


export function HardwareInfoCreate(reuqest){
  return axios.post('http://localhost:9011/hardware_info/save',reuqest);
}export function HardwareInfoUpdate(reuqest){
  return axios.post('http://localhost:9011/hardware_info/update',reuqest);
}
export function HardwareInfoById(id){
  return axios.get('http://localhost:9011/hardware_info/byId/' + id);
}