import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class database<T extends Marker> {
    public ArrayCollection<T> allMarkers = new ArrayCollection<T>();
    
    public database(){
        
    }
    
    // Given some location, return an int of the number of signs in the given location. 
    public int NumberOfSignsInLocation(String location){
        int count = 0;
        Object[] array = allMarkers.elements;
        for(int i=0; i< allMarkers.numElements; i++){
            Object obj = array[i];
    
            if(obj==null) continue;
            Marker marker = (Marker) obj;
            if(marker.getSignCounty() == null) continue;
            if (marker.getSignCounty().equalsIgnoreCase(location)){
                count++;
            }
        }
        return count;
    }

    // Given some location and direction, return the number of matching signs 
    public int LocationAndDirection(String location, String direction){
        int count = 0;
        Object[] array = allMarkers.elements;
        for(int i=0; i<allMarkers.numElements; i++){
            Object obj = array[i];
            if(obj == null) continue;
            Marker marker = (Marker) obj;
            if(marker.getSignCounty() == null || marker.getSignTravelDirection()==null ) continue;
            if (marker.getSignCounty().equalsIgnoreCase(location) && marker.getSignTravelDirection().equalsIgnoreCase(direction)){
                count++;
            }
        }
        return count;
    }
    // Top-N counties by sign count
    public List<Map.Entry<String,Integer>> topNCounties(int n){
        List<Map.Entry<String,Integer>> arrayList = new ArrayList<>();
        if(n==0) return null;

        TreeMap<String, Integer> numCounty = new TreeMap<>();

        for(int i =0; i<allMarkers.numElements;i++){
            Object obj = allMarkers.elements[i];
            if(obj==null) continue;
            Marker marker = (Marker) obj;

            String county= marker.getSignCounty();
            if(county == null) continue;
            
            Integer count = numCounty.get(county);
            if(count==null) count =0;
            numCounty.put(county, count+1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(numCounty.entrySet());
        for(int i = 0; i<n; i++){
            arrayList.add(list.get(i));
        }
        return arrayList;
    }

    // Top-N routes by sign count
    public List<Map.Entry<String,Integer>> topNRoutes(int n){
        List<Map.Entry<String,Integer>> arrayList = new ArrayList<>();
        if(n==0) return null;

        TreeMap<String, Integer> numRoutes = new TreeMap<>();

        for(int i =0; i<allMarkers.numElements;i++){
            Object obj = allMarkers.elements[i];
            if(obj==null) continue;
            Marker marker = (Marker) obj;

            String route= marker.getSignRoute();
            if(route == null) continue;
            
            Integer count = numRoutes.get(route);
            if(count==null) count =0;
            numRoutes.put(route, count+1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(numRoutes.entrySet());
        for(int i = 0; i<n; i++){
            arrayList.add(list.get(i));
        }
        return arrayList;
    }    
}