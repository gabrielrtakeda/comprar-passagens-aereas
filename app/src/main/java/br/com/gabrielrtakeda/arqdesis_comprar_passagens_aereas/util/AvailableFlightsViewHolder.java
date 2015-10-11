package br.com.gabrielrtakeda.arqdesis_comprar_passagens_aereas.util;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gtakeda on 10/9/15.
 */
public class AvailableFlightsViewHolder {
    ImageView image;
    TextView origin;
    TextView data;
    TextView arrival;
    TextView description;

    public AvailableFlightsViewHolder(
            ImageView image,
            TextView origin,
            TextView data,
            TextView arrival,
            TextView description
    ) {
        this.setImage(image)
            .setOrigin(origin)
            .setData(data)
            .setArrival(arrival)
            .setDescription(description);
    }

    public ImageView getImage() {
        return image;
    }

    public AvailableFlightsViewHolder setImage(ImageView image) {
        this.image = image;
        return this;
    }

    public TextView getOrigin() {
        return origin;
    }

    public AvailableFlightsViewHolder setOrigin(TextView origin) {
        this.origin = origin;
        return this;
    }

    public TextView getData() {
        return data;
    }

    public AvailableFlightsViewHolder setData(TextView data) {
        this.data = data;
        return this;
    }

    public TextView getArrival() {
        return arrival;
    }

    public AvailableFlightsViewHolder setArrival(TextView arrival) {
        this.arrival = arrival;
        return this;
    }

    public TextView getDescription() {
        return description;
    }

    public AvailableFlightsViewHolder setDescription(TextView description) {
        this.description = description;
        return this;
    }
}
