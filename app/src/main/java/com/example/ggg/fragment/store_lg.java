//package com.example.ggg.fragment;
//
//public class store_lg {
//    private lateinit var binding: FragmentHome2Binding
//    private lateinit var homeMvvm: HomeViewModel
//    private lateinit var randomSticker: Meal
//
//    ///
//
//    companion object{
//        const val MEAL_ID = "com.example.myapplication.youtubeVD.fragment.idMeal"
//        const val MEAL_NAME = "com.example.myapplication.youtubeVD.fragment.nameMeal"
//        const val MEAL_THUMB = "com.example.myapplication.youtubeVD.fragment.thumbMeal"
//    }
//
//    //
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]
//
//
//    }
//
//    override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentHome2Binding.inflate(inflater,container,false)
//        return binding.root
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        homeMvvm.getRamdomMeal()
//        observerRamdomMeal()
//
//
//    }
//
//    /////
//
//    private fun onRamdomMealClick(){
//        binding.powerpuffGirlImg.setOnClickListener {
//            val intent = Intent(activity, StickerActivityDetail::class.java)
//
//            intent.putExtra(MEAL_ID,randomSticker.idMeal)
//            intent.putExtra(MEAL_NAME,randomSticker.strMeal)
//            intent.putExtra(MEAL_THUMB,randomSticker.strMealThumb)
//
//            startActivity(intent)
//        }
//
//
//    }
//
//    /////
//
//
//    private fun observerRamdomMeal(){
//        homeMvvm.observeRandomMealLivedata().observe(viewLifecycleOwner,object : Observer<Meal>{
//            override fun onChanged(t: Meal) {
//                Glide.with(this@HomeFragment2)
//                    .load(t!!.strMealThumb)
//                    .into(binding.powerpuffGirlImg)
//            }
//
//        })
//    }
//}
