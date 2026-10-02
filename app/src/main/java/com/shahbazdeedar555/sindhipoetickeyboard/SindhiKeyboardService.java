

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/keyboard_root"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:minHeight="300dp"
    android:orientation="vertical"
    android:background="#173F35"
    android:padding="2dp">

    <!-- ================================================= -->
    <!-- NORMAL PAGE -->
    <!-- ================================================= -->

    <LinearLayout
        android:id="@+id/keyboard_page1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:visibility="visible">

        <!-- ROW 1 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="58dp"
            android:orientation="horizontal">

            <Button android:id="@+id/key_alif" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="چ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_bay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڇ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_bay2" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="پ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_pay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="و" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_bhe" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڳ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_te" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ع" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_the" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ٿ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_tt" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ت" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_say" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ر" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_fay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ي" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_fhay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ص" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_gaf" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ق" android:textSize="23sp" android:textStyle="bold"/>
        </LinearLayout>

        <!-- ROW 2 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="58dp"
            android:orientation="horizontal">

            <Button android:id="@+id/key_gaf2" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڍ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_gn" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڱ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_kaf" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ک" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_yay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ل" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_dal" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڪ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_dhal" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ج" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_dhad" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ه" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_dde" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="گ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_dd" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ف" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_ddh" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="د" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_hay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="س" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_jeem" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ا" android:textSize="23sp" android:textStyle="bold"/>
        </LinearLayout>

        <!-- ROW 3 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="58dp"
            android:orientation="horizontal">

            <Button android:id="@+id/key_delete" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="⌫" android:textSize="30sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_jay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ئ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_nje" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="م" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_chay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ن" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_chhe" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ب" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_khay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڀ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_ain" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ط" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_ghain" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="خ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_ray" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ز" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_shift" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="⇧" android:textSize="30sp" android:textStyle="bold"/>
        </LinearLayout>

        <!-- BOTTOM -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="58dp"
            android:orientation="horizontal">

            <Button android:id="@+id/key_enter" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:text="↵" android:textSize="27sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_tatweel" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ـ" android:textSize="30sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_comma" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="،" android:textSize="25sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_period" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="." android:textSize="25sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_space" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="3" android:text="Space" android:textSize="19sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_123" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:text="123" android:textSize="19sp" android:textStyle="bold"/>
        </LinearLayout>

    </LinearLayout>


    <!-- ================================================= -->
    <!-- SHIFT PAGE -->
    <!-- ================================================= -->

    <LinearLayout
        android:id="@+id/keyboard_page2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:visibility="gone">

        <!-- ROW 1 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="58dp"
            android:orientation="horizontal">

            <Button android:id="@+id/key_rre" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڄ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_meem" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڃ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_nun" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڦ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_shift_fatha" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ُ" android:textSize="30sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_lam" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ھ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_sin" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="غ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_sheen" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ث" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_sad" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ٽ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_dad" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڙ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_tay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ض" android:textSize="23sp" android:textStyle="bold"/>
        </LinearLayout>

        <!-- ROW 2 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="58dp"
            android:orientation="horizontal">

            <Button android:id="@+id/key_zay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ٺ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_nnoon" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڌ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_waw" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڏ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_hay2" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="۽" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_jhay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ۡ" android:textSize="30sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_kay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ح" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_ghay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڦ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_hamza" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڊ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_he" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ش" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_ya" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="آ" android:textSize="23sp" android:textStyle="bold"/>
        </LinearLayout>

        <!-- ROW 3 -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="58dp"
            android:orientation="horizontal">

            <Button android:id="@+id/key_delete2" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="⌫" android:textSize="30sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_yeh" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="۾" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_waw2" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ڻ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_zhay" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ٻ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_yay2" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ء" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_shift_extra" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ظ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_shift_diacritic" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ّ" android:textSize="30sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_shift_dhal" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ذ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_shift_back" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="⇧" android:textSize="30sp" android:textStyle="bold"/>
        </LinearLayout>

        <!-- BOTTOM -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="58dp"
            android:orientation="horizontal">

            <Button android:id="@+id/key_enter2" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:text="↵" android:textSize="27sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_zabar" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="َ" android:textSize="30sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_zer" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ِ" android:textSize="30sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_space2" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="3" android:text="Space" android:textSize="19sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_123_shift" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:text="123" android:textSize="19sp" android:textStyle="bold"/>
        </LinearLayout>

    </LinearLayout>


    <!-- ================================================= -->
    <!-- 123 PAGE -->
    <!-- ================================================= -->

    <LinearLayout
        android:id="@+id/keyboard_page3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:visibility="gone">

        <!-- NUMBERS -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="58dp"
            android:orientation="horizontal">

            <Button android:id="@+id/key_num_0" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="0" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_num_9" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="9" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_num_8" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="8" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_num_7" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="7" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_num_6" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="6" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_num_5" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="5" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_num_4" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="4" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_num_3" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="3" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_num_2" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="2" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_num_1" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="1" android:textSize="23sp" android:textStyle="bold"/>
        </LinearLayout>

        <!-- SPECIAL CHARACTERS -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="58dp"
            android:orientation="horizontal">

            <Button android:id="@+id/key_s1" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="(" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s2" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text=")" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s3" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="=" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s4" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="-" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s5" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="*" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s6" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ٰ" android:textSize="28sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s7" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ة" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s8" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ؤ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s9" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ہ" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s10" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="بہ" android:textSize="21sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s11" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ى" android:textSize="23sp" android:textStyle="bold"/>
        </LinearLayout>

        <!-- DIACRITICS / SIGNS -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="58dp"
            android:orientation="horizontal">

            <Button android:id="@+id/key_num_delete" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:text="⌫" android:textSize="30sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s12" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ٖ" android:textSize="28sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s13" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ً" android:textSize="28sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s14" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="'" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s15" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="؟" android:textSize="25sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s16" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="ٗ" android:textSize="28sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s17" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text="؛" android:textSize="25sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_s18" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1" android:text=":" android:textSize="25sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_num_shift" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:text="⇧" android:textSize="30sp" android:textStyle="bold"/>
        </LinearLayout>

        <!-- BOTTOM -->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="58dp"
            android:orientation="horizontal">

            <Button android:id="@+id/key_num_enter" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:text="↵" android:textSize="27sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_quotes" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:text="“”" android:textSize="23sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_num_space" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="3" android:text="Space" android:textSize="19sp" android:textStyle="bold"/>
            <Button android:id="@+id/key_ibt" android:layout_width="0dp" android:layout_height="match_parent" android:layout_weight="1.2" android:text="ا ب ت" android:textSize="15sp" android:textStyle="bold"/>
        </LinearLayout>

    </LinearLayout>

</LinearLayout>
2. SindhiKeyboardService.java
هي اهم آهي: هن ۾ توهان جو موجوده Delete + Long Delete ۽ Enter وارو logic ساڳيو رکيو ويو آهي.
package com.shahbazdeedar555.sindhipoetickeyboard;

import android.graphics.Color;
import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.os.Handler;
import android.os.Looper;


// ========================================
// SINDHI POETIC KEYBOARD SERVICE
// ========================================

public class SindhiKeyboardService extends InputMethodService {

    private View keyboard;

    private final Handler deleteHandler =
            new Handler(Looper.getMainLooper());

    private boolean fastDeleting = false;


    // ========================================
    // FAST DELETE
    // ========================================

    private final Runnable deleteRunnable = new Runnable() {

        @Override
        public void run() {

            if (!fastDeleting) {
                return;
            }

            deleteOneCharacter();

            deleteHandler.postDelayed(this, 70);
        }
    };


    // ========================================
    // CREATE KEYBOARD
    // ========================================

    @Override
    public View onCreateInputView() {

        keyboard = getLayoutInflater().inflate(
                R.layout.keyboard_view,
                null
        );

        setAllButtonsStyle(keyboard);


        // ========================================
        // NORMAL LETTERS
        // ========================================

        setKey(R.id.key_alif, "چ");
        setKey(R.id.key_bay, "ڇ");
        setKey(R.id.key_bay2, "پ");
        setKey(R.id.key_pay, "و");
        setKey(R.id.key_bhe, "ڳ");
        setKey(R.id.key_te, "ع");
        setKey(R.id.key_the, "ٿ");
        setKey(R.id.key_tt, "ت");

        setKey(R.id.key_say, "ر");
        setKey(R.id.key_fay, "ي");
        setKey(R.id.key_fhay, "ص");
        setKey(R.id.key_gaf, "ق");

        setKey(R.id.key_gaf2, "ڍ");
        setKey(R.id.key_gn, "ڱ");
        setKey(R.id.key_kaf, "ک");
        setKey(R.id.key_yay, "ل");
        setKey(R.id.key_dal, "ڪ");
        setKey(R.id.key_dhal, "ج");
        setKey(R.id.key_dhad, "ه");
        setKey(R.id.key_dde, "گ");
        setKey(R.id.key_dd, "ف");
        setKey(R.id.key_ddh, "د");
        setKey(R.id.key_hay, "س");
        setKey(R.id.key_jeem, "ا");

        setKey(R.id.key_jay, "ئ");
        setKey(R.id.key_nje, "م");
        setKey(R.id.key_chay, "ن");
        setKey(R.id.key_chhe, "ب");
        setKey(R.id.key_khay, "ڀ");
        setKey(R.id.key_ain, "ط");
        setKey(R.id.key_ghain, "خ");
        setKey(R.id.key_ray, "ز");


        // ========================================
        // SHIFT LETTERS
        // ========================================

        setKey(R.id.key_rre, "ڄ");
        setKey(R.id.key_meem, "ڃ");
        setKey(R.id.key_nun, "ڦ");
        setKey(R.id.key_shift_fatha, "ُ");
        setKey(R.id.key_lam, "ھ");
        setKey(R.id.key_sin, "غ");
        setKey(R.id.key_sheen, "ث");
        setKey(R.id.key_sad, "ٽ");
        setKey(R.id.key_dad, "ڙ");
        setKey(R.id.key_tay, "ض");

        setKey(R.id.key_zay, "ٺ");
        setKey(R.id.key_nnoon, "ڌ");
        setKey(R.id.key_waw, "ڏ");
        setKey(R.id.key_hay2, "۽");
        setKey(R.id.key_jhay, "ۡ");
        setKey(R.id.key_kay, "ح");
        setKey(R.id.key_ghay, "ڦ");
        setKey(R.id.key_hamza, "ڊ");
        setKey(R.id.key_he, "ش");
        setKey(R.id.key_ya, "آ");

        setKey(R.id.key_yeh, "۾");
        setKey(R.id.key_waw2, "ڻ");
        setKey(R.id.key_zhay, "ٻ");
        setKey(R.id.key_yay2, "ء");
        setKey(R.id.key_shift_extra, "ظ");
        setKey(R.id.key_shift_diacritic, "ّ");
        setKey(R.id.key_shift_dhal, "ذ");


        // ========================================
        // 123 PAGE
        // ========================================

        setKey(R.id.key_num_0, "0");
        setKey(R.id.key_num_9, "9");
        setKey(R.id.key_num_8, "8");
        setKey(R.id.key_num_7, "7");
        setKey(R.id.key_num_6, "6");
        setKey(R.id.key_num_5, "5");
        setKey(R.id.key_num_4, "4");
        setKey(R.id.key_num_3, "3");
        setKey(R.id.key_num_2, "2");
        setKey(R.id.key_num_1, "1");

        setKey(R.id.key_s1, "(");
        setKey(R.id.key_s2, ")");
        setKey(R.id.key_s3, "=");
        setKey(R.id.key_s4, "-");
        setKey(R.id.key_s5, "*");
        setKey(R.id.key_s6, "ٰ");
        setKey(R.id.key_s7, "ة");
        setKey(R.id.key_s8, "ؤ");
        setKey(R.id.key_s9, "ہ");
        setKey(R.id.key_s10, "بہ");
        setKey(R.id.key_s11, "ى");

        setKey(R.id.key_s12, "ٖ");
        setKey(R.id.key_s13, "ً");
        setKey(R.id.key_s14, "'");
        setKey(R.id.key_s15, "؟");
        setKey(R.id.key_s16, "ٗ");
        setKey(R.id.key_s17, "؛");
        setKey(R.id.key_s18, ":");


        // ========================================
        // TATWEEL
        // ========================================

        setKey(R.id.key_tatweel, "ـ");


        // ========================================
        // COMMA + PERIOD
        // ========================================

        setKey(R.id.key_comma, "،");
        setKey(R.id.key_period, ".");


        // ========================================
        // QUOTES
        // ========================================

        setKey(R.id.key_quotes, "“”");


        // ========================================
        // DELETE + LONG DELETE
        // ========================================

        Button delete =
                keyboard.findViewById(R.id.key_delete);

        if (delete != null) {

            delete.setOnClickListener(v -> {
                deleteOneCharacter();
            });

            delete.setOnLongClickListener(v -> {

                fastDeleting = true;

                deleteOneCharacter();

                deleteHandler.postDelayed(
                        deleteRunnable,
                        180
                );

                return true;
            });

            delete.setOnTouchListener((v, event) -> {

                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {

                    fastDeleting = false;

                    deleteHandler.removeCallbacks(
                            deleteRunnable
                    );
                }

                return false;
            });
        }


        // ========================================
        // SPACE
        // ========================================

        setupSpace(R.id.key_space);
        setupSpace(R.id.key_space2);
        setupSpace(R.id.key_num_space);


        // ========================================
        // ENTER
        // ========================================

        setupEnter(R.id.key_enter);
        setupEnter(R.id.key_enter2);
        setupEnter(R.id.key_num_enter);


        // ========================================
        // SHIFT
        // ========================================

        setupShift(R.id.key_shift);
        setupShift(R.id.key_shift_back);
        setupShift(R.id.key_num_shift);


        // ========================================
        // 123 BUTTONS
        // ========================================

        Button normal123 =
                keyboard.findViewById(R.id.key_123);

        if (normal123 != null) {

            normal123.setOnClickListener(v ->
                    showPage3()
            );
        }


        Button shift123 =
                keyboard.findViewById(R.id.key_123_shift);

        if (shift123 != null) {

            shift123.setOnClickListener(v ->
                    showPage3()
            );
        }


        // ========================================
        // ا ب ت
        // RETURN TO NORMAL
        // ========================================

        Button alphabet =
                keyboard.findViewById(R.id.key_ibt);

        if (alphabet != null) {

            alphabet.setOnClickListener(v ->
                    showPage1()
            );
        }


        return keyboard;
    }


    // ========================================
    // PAGE 1
    // ========================================

    private void showPage1() {

        View page1 =
                keyboard.findViewById(R.id.keyboard_page1);

        View page2 =
                keyboard.findViewById(R.id.keyboard_page2);

        View page3 =
                keyboard.findViewById(R.id.keyboard_page3);

        if (page1 != null) {
            page1.setVisibility(View.VISIBLE);
        }

        if (page2 != null) {
            page2.setVisibility(View.GONE);
        }

        if (page3 != null) {
            page3.setVisibility(View.GONE);
        }
    }


    // ========================================
    // PAGE 2
    // ========================================

    private void showPage2() {

        View page1 =
                keyboard.findViewById(R.id.keyboard_page1);

        View page2 =
                keyboard.findViewById(R.id.keyboard_page2);

        View page3 =
                keyboard.findViewById(R.id.keyboard_page3);

        if (page1 != null) {
            page1.setVisibility(View.GONE);
        }

        if (page2 != null) {
            page2.setVisibility(View.VISIBLE);
        }

        if (page3 != null) {
            page3.setVisibility(View.GONE);
        }
    }


    // ========================================
    // PAGE 3
    // ========================================

    private void showPage3() {

        View page1 =
                keyboard.findViewById(R.id.keyboard_page1);

        View page2 =
                keyboard.findViewById(R.id.keyboard_page2);

        View page3 =
                keyboard.findViewById(R.id.keyboard_page3);

        if (page1 != null) {
            page1.setVisibility(View.GONE);
        }

        if (page2 != null) {
            page2.setVisibility(View.GONE);
        }

        if (page3 != null) {
            page3.setVisibility(View.VISIBLE);
        }
    }


    // ========================================
    // SHIFT
    // ========================================

    private void setupShift(int id) {

        Button shift =
                keyboard.findViewById(id);

        if (shift != null) {

            shift.setOnClickListener(v ->
                    showPage2()
            );
        }
    }


    // ========================================
    // SPACE
    // ========================================

    private void setupSpace(int id) {

        Button space =
                keyboard.findViewById(id);

        if (space != null) {

            space.setOnClickListener(v -> {

                if (getCurrentInputConnection() != null) {

                    getCurrentInputConnection()
                            .commitText(" ", 1);
                }
            });
        }
    }


    // ========================================
    // ENTER
    // ========================================

    private void setupEnter(int id) {

        Button enter =
                keyboard.findViewById(id);

        if (enter != null) {

            enter.setOnClickListener(v -> {

                if (getCurrentInputConnection() == null) {
                    return;
                }

                getCurrentInputConnection()
                        .sendKeyEvent(
                                new KeyEvent(
                                        KeyEvent.ACTION_DOWN,
                                        KeyEvent.KEYCODE_ENTER
                                )
                        );

                getCurrentInputConnection()
                        .sendKeyEvent(
                                new KeyEvent(
                                        KeyEvent.ACTION_UP,
                                        KeyEvent.KEYCODE_ENTER
                                )
                        );
            });
        }
    }


    // ========================================
    // DELETE ONE CHARACTER
    // ========================================

    private void deleteOneCharacter() {

        if (getCurrentInputConnection() != null) {

            getCurrentInputConnection()
                    .deleteSurroundingText(1, 0);
        }
    }


    // ========================================
    // NORMAL KEY
    // ========================================

    private void setKey(int id, String text) {

        Button button =
                keyboard.findViewById(id);

        if (button != null) {

            button.setEnabled(true);
            button.setClickable(true);

            button.setOnClickListener(v -> {

                if (getCurrentInputConnection() != null) {

                    getCurrentInputConnection()
                            .commitText(text, 1);
                }
            });
        }
    }


    // ========================================
    // BLACK BUTTONS / WHITE TEXT
    // ========================================

    private void setAllButtonsStyle(View view) {

        if (view instanceof Button) {

            Button button = (Button) view;

            button.setBackgroundColor(Color.BLACK);
            button.setTextColor(Color.WHITE);

        } else if (view instanceof android.view.ViewGroup) {

            android.view.ViewGroup group =
                    (android.view.ViewGroup) view;

            for (int i = 0; i < group.getChildCount(); i++) {

                setAllButtonsStyle(
                        group.getChildAt(i)
                );
            }
        }
    }
}
