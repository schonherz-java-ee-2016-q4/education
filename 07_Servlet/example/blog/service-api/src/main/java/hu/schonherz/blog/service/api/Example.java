
package hu.schonherz.blog.service.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Example {

@SerializedName("results")
@Expose
private List<Result> results = new ArrayList<Result>();
@SerializedName("info")
@Expose
private Info info;

/**
* 
* @return
* The results
*/
public List<Result> getResults() {
return results;
}

/**
* 
* @param results
* The results
*/
public void setResults(List<Result> results) {
this.results = results;
}

/**
* 
* @return
* The info
*/
public Info getInfo() {
return info;
}

/**
* 
* @param info
* The info
*/
public void setInfo(Info info) {
this.info = info;
}

}

