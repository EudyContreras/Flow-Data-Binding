# Data Binding Example

This project showcases the use of DataBinding in combination with StateFlows. It uses the repository pattern
in which both a local source and a remote source is used in order to fetch data.

![Android Data Binding!](https://miro.medium.com/max/1400/1*Q5hIJdowXgmLp7drcyoDdg.png)

### Why DataBinding?

Data binding allows us to bind data to our views using a declarative approach. This makes it easier
to both reuse our ViewModels and have better separation of concerns.

**Example of how data binding looks compared to other approaches:**

### FindViewById
```kotlin
findViewById<TextView>(R.id.sample_text).apply {
    text = viewModel.userName
}
````
### ViewBinding
```kotlin
bidning.sampleText.text = viewModel.userName
````
### DataBinding
```kotlin
<TextView android:text="@{viewmodel.userName}" />
```

## Pros and Cons of DataBinding

Using DataBinding over ViewBinding has some advantages and disadvantages.

**Pros**
* Ability to bind observable values to our views. This removes the need of explicitly writing update logic
* We can bind our StateFlows and LiveData directly to our views and no need of clearing observers anymore
* Two way binding allows our views to update our source and the source to update the our view. (A spinner)
* Cuts down on complexity and features faster development time, easier to maintain and more readable code.
* Use of binding adapter for more complex expressions. This reduces the need of custom views
* Offers Binding Expressions which allows us to write expressions the connect variables to the views in the layout.
* Easier to represent our UI based on states with the help of (BindingSwitchers)
* Helps us organize our code using immutable states (Immutability == Predictability)¨
* Better ViewModel reusability
* Makes the transition to a more declarative UI framework easier (Jetpack Compose)

**Cons**
* Slower compilation time: Data binding requires annotation processing, so compile times are slower.

>In short, there is nothing View Binding can do that Data Binding cannot,(though at the expense of longer build times) and there is a lot that Data Binding can do >that View Binding cannot do.

### Usage

There are some common patterns used when working with DataBinding. Here are some common ones:

* We can determine which layout to render based on the type of our data.
```kotlin
  private val binding: ItemBinding<PolymorphicData> = { data ->
        when (data) {
            is PolymorphicData.TypeA -> R.layout.layout_type_a
            is PolymorphicData.TypeB -> R.layout.layout_type_b
        }
    }
```

Lets take a look at a common example that binds a url to an ImageView.
In the example below we are binding an URL and a placeHolder to our ImageView.
Our placeHolder is a Drawable which is shown if the image fails to load

```xml
 <ImageView
    android:layout_width="100dp"
    android:layout_height="100dp" // we can even specify the size if needed: 
    app:imageUrl="@{viewModel.imageUrl}"
    app:placeholder="@{@drawable/img_male_avatar}" />
```

In order for this to work we can write a simple BindingAdapter which can look like this:
```kotlin
@BindingAdapter(value = ["imageUrl", "placeholder"])
fun ImageView.loadImage(imageUrl: String?, placeholder: Drawable) {
    if (imageUrl == null) return
    Glide.with(context)
        .load(imageUrl)
        .fallback(placeholder)
        .placeholder(placeholder)
        .into(this)
}
```

### Want to know more?

You can read more about DataBinding at the [Official Website](https://developer.android.com/topic/libraries/data-binding)