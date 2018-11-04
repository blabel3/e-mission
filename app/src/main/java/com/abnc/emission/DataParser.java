package com.abnc.emission;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataParser {

    private int offset;
    private boolean more;

    public DataParser(){
        offset = 0;
        more = true;
    }

    public DataParser(int offset, boolean hasMore){
        this.offset = offset;
        this.more = hasMore;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isMore() {
        return more;
    }

    public List<Car> readJSON(InputStream json) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(json, "UTF-8"));

        try {
            //reading the messages and returning a value

            /*check for pagination settings (e.x. offset and hasMore)
            String resultsName = reader.nextName();
            while(!resultsName.equals("results")){
                if(resultsName.equals("has_more")){
                    more = reader.nextBoolean();
                } else if(resultsName.equals("next_offset")){
                    offset = reader.nextInt();
                }

                resultsName = reader.nextName();
            }
            //then get deviation results*/

            reader.beginObject();

            return readCars(reader);

        } finally {
            reader.close();
        }

    }

    public List<Car> readCars(JsonReader reader) throws IOException {
        ArrayList<Car> cars = new ArrayList<Car>();

        reader.beginArray();

        while (reader.hasNext()){
            //check that there are more objects to add
            cars.add(readCar(reader));
        }

        reader.endArray();
        return cars;
    }

    public Car readCar(JsonReader reader) throws IOException {
        //all possible data to get from a deviation as variables

        String make = "";
        String model = "";
        int year = 0;
        int MPG = 0;

        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            switch (name) {
                case "make": make = reader.nextString(); //app does not support buying prints now.
                    break;
                case "model": model = reader.nextString(); //app does not support buying prints now.
                    break;
                case "year": year = reader.nextInt(); //app does not support buying prints now.
                    break;
                case "MPG": MPG = reader.nextInt(); //app does not support buying prints now.
                    break;
                default: reader.skipValue();
                    break;
            }
        }
        reader.endObject();

        return new Car(make, model, year, MPG);

    }

    /*public ArrayList<DASubmission> translateDeviations (JSONArray results) throws JSONException{

        ArrayList<DASubmission> submissions = new ArrayList<DASubmission>();

        for(int i = 0; i < results.length(); i++){
            JSONObject submission = results.getJSONObject(i);

            submissions.add(translateDeviation(submission));

        }

        return submissions;

    }

    public DASubmission translateDeviation(JSONObject submission) throws JSONException{

        UUID id = UUID.fromString(submission.optString("deviationid", null));
        String url = submission.optString("url", null);
        String title = submission.optString("title", null);
        String category = submission.optString("category", null);
        boolean favorited = submission.optBoolean("is_favorited", false);
        boolean deleted = submission.optBoolean("is_deleted", false);
        DAUser author = new DAUser(submission.optJSONObject("author"));
        long time = submission.optLong("published_time", 0);
        boolean comments = submission.optBoolean("allows_comments", true);
        boolean mature = submission.optBoolean("is_mature", false);
        String previewSource = null;
        int previewHeight = 0;
        int previewWidth = 0;
        if(submission.optJSONObject("preview") != null){
            previewSource = submission.optJSONObject("preview").optString("src", null);
            previewHeight = submission.optJSONObject("preview").optInt("height", 0);
            previewWidth = submission.optJSONObject("preview").optInt("width", 0);
        }
        String contentSource = null;
        int contentHeight = 0;
        int contentWidth = 0;
        if(submission.optJSONObject("content") != null) {
            contentSource = submission.optJSONObject("content").optString("src", null);
            contentHeight = submission.optJSONObject("content").optInt("height", 0);
            contentWidth = submission.optJSONObject("content").optInt("width", 0);
        }
        ArrayList<DAThumb> thumbs = new ArrayList<DAThumb>();
        String excerpt = submission.optString("excerpt", null);
        String formattedExcerpt = submission.optString("formatted_excerpt", null);
        //String content = null;

        DASubmission sharedData = new DASubmission(id, url, title, category, favorited, deleted, author, time, comments, mature);

        if (excerpt != null){
            return new DAWriting(sharedData, excerpt, formattedExcerpt, null);
        } else if(previewSource != null || contentSource != null){
            return new DAPicture(sharedData, previewSource, previewHeight, previewWidth,
                    contentSource, contentHeight, contentWidth, thumbs);
        } else {
            //should never happen, fix if this does but just in case
            return sharedData;
        }
    }*/
}
