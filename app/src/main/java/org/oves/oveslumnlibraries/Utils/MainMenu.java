package org.oves.oveslumnlibraries.Utils;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import org.oves.oveslumnlibraries.R;
import org.oves.oveslumnlibraries.Utils.MnAdapter.Item;

import java.util.ArrayList;
import java.util.List;

//import org.oves.lumnlibrarypackage.activity.about.AboutApp;
//import org.oves.lumnlibrarypackage.activity.about.AboutAppSimple;
//import org.oves.lumnlibrarypackage.activity.about.AboutAppSimpleBlue;
//import org.oves.lumnlibrarypackage.activity.about.AboutCompany;
//import org.oves.lumnlibrarypackage.activity.about.AboutCompanyCard;
//import org.oves.lumnlibrarypackage.activity.about.AboutCompanyImage;
//import org.oves.lumnlibrarypackage.activity.about.AboutDialogMainAction;
//import org.oves.lumnlibrarypackage.activity.article.ArticleBigHeader;
//import org.oves.lumnlibrarypackage.activity.article.ArticleBookReview;
//import org.oves.lumnlibrarypackage.activity.article.ArticleCard;
//import org.oves.lumnlibrarypackage.activity.article.ArticleDetails;
//import org.oves.lumnlibrarypackage.activity.article.ArticleFood;
//import org.oves.lumnlibrarypackage.activity.article.ArticleFoodReview;
//import org.oves.lumnlibrarypackage.activity.article.ArticleMedium;
//import org.oves.lumnlibrarypackage.activity.article.ArticleMediumDark;
//import org.oves.lumnlibrarypackage.activity.article.ArticleSimple;
//import org.oves.lumnlibrarypackage.activity.article.ArticleStepper;
//import org.oves.lumnlibrarypackage.activity.bottomnavigation.BottomNavigationArticle;
//import org.oves.lumnlibrarypackage.activity.bottomnavigation.BottomNavigationBasic;
//import org.oves.lumnlibrarypackage.activity.bottomnavigation.BottomNavigationDark;
//import org.oves.lumnlibrarypackage.activity.bottomnavigation.BottomNavigationIcon;
//import org.oves.lumnlibrarypackage.activity.bottomnavigation.BottomNavigationLight;
//import org.oves.lumnlibrarypackage.activity.bottomnavigation.BottomNavigationLightSimple;
//import org.oves.lumnlibrarypackage.activity.bottomnavigation.BottomNavigationMain;
//import org.oves.lumnlibrarypackage.activity.bottomnavigation.BottomNavigationMapBlue;
//import org.oves.lumnlibrarypackage.activity.bottomnavigation.BottomNavigationPrimary;
//import org.oves.lumnlibrarypackage.activity.bottomnavigation.BottomNavigationShifting;
//import org.oves.lumnlibrarypackage.activity.bottomnavigation.BottomNavigationShop;
//import org.oves.lumnlibrarypackage.activity.bottomnavigation.BottomNavigationSmall;
//import org.oves.lumnlibrarypackage.activity.bottomsheet.BottomSheetBasic;
//import org.oves.lumnlibrarypackage.activity.bottomsheet.BottomSheetFilter;
//import org.oves.lumnlibrarypackage.activity.bottomsheet.BottomSheetFloating;
//import org.oves.lumnlibrarypackage.activity.bottomsheet.BottomSheetFull;
//import org.oves.lumnlibrarypackage.activity.bottomsheet.BottomSheetList;
//import org.oves.lumnlibrarypackage.activity.bottomsheet.BottomSheetMap;
//import org.oves.lumnlibrarypackage.activity.bottomsheet.BottomSheetMenu;
//import org.oves.lumnlibrarypackage.activity.button.ButtonBasic;
//import org.oves.lumnlibrarypackage.activity.button.ButtonInUtilities;
//import org.oves.lumnlibrarypackage.activity.button.ButtonToggleBasic;
//import org.oves.lumnlibrarypackage.activity.button.FabExtend;
//import org.oves.lumnlibrarypackage.activity.button.FabMiddle;
//import org.oves.lumnlibrarypackage.activity.button.FabMore;
//import org.oves.lumnlibrarypackage.activity.button.FabMoreText;
//import org.oves.lumnlibrarypackage.activity.card.CardBasic;
//import org.oves.lumnlibrarypackage.activity.card.CardCheckable;
//import org.oves.lumnlibrarypackage.activity.card.CardOverlap;
//import org.oves.lumnlibrarypackage.activity.card.CardTimeline;
//import org.oves.lumnlibrarypackage.activity.card.CardWizard;
//import org.oves.lumnlibrarypackage.activity.card.CardWizardLight;
//import org.oves.lumnlibrarypackage.activity.card.CardWizardOverlap;
//import org.oves.lumnlibrarypackage.activity.chat.ChatBBM;
//import org.oves.lumnlibrarypackage.activity.chat.ChatFacebook;
//import org.oves.lumnlibrarypackage.activity.chat.ChatTelegram;
//import org.oves.lumnlibrarypackage.activity.chat.ChatWhatsapp;
//import org.oves.lumnlibrarypackage.activity.chip.ChipBasic;
//import org.oves.lumnlibrarypackage.activity.chip.ChipTag;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardCoursesProgress;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardCryptocurrency;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardDana;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardFinance;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardFlight;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardGridFab;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardNews;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardPayBill;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardStatistics;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardTravel;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardTravelPackage;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardWallet;
//import org.oves.lumnlibrarypackage.activity.dashboard.DashboardWalletGreen;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogAchievement;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogAddPost;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogAddReview;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogBasic;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogContactUs;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogCustom;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogCustomDark;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogCustomInfo;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogCustomLight;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogCustomWarning;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogFullscreen;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogGDPRBasic;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogHeader;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogImage;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogMenuPayment;
//import org.oves.lumnlibrarypackage.activity.dialog.DialogTermOfServices;
//import org.oves.lumnlibrarypackage.activity.expansionpanel.ExpansionPanelBasic;
//import org.oves.lumnlibrarypackage.activity.expansionpanel.ExpansionPanelInvoice;
//import org.oves.lumnlibrarypackage.activity.expansionpanel.ExpansionPanelTicket;
//import org.oves.lumnlibrarypackage.activity.form.FormAddProject;
//import org.oves.lumnlibrarypackage.activity.form.FormAddProjectBlue;
//import org.oves.lumnlibrarypackage.activity.form.FormAddress;
//import org.oves.lumnlibrarypackage.activity.form.FormCheckout;
//import org.oves.lumnlibrarypackage.activity.form.FormEcommerce;
//import org.oves.lumnlibrarypackage.activity.form.FormInputOutlined;
//import org.oves.lumnlibrarypackage.activity.form.FormLogin;
//import org.oves.lumnlibrarypackage.activity.form.FormProfileData;
//import org.oves.lumnlibrarypackage.activity.form.FormSignUp;
//import org.oves.lumnlibrarypackage.activity.form.FormSignupCard;
//import org.oves.lumnlibrarypackage.activity.form.FormSignupCardStack;
//import org.oves.lumnlibrarypackage.activity.form.FormSignupDark;
//import org.oves.lumnlibrarypackage.activity.form.FormSignupImage;
//import org.oves.lumnlibrarypackage.activity.form.FormSignupImageCard;
//import org.oves.lumnlibrarypackage.activity.form.FormSignupImageOutline;
//import org.oves.lumnlibrarypackage.activity.form.FormTextArea;
//import org.oves.lumnlibrarypackage.activity.form.FormWithIcon;
//import org.oves.lumnlibrarypackage.activity.gridlist.GridAlbums;
//import org.oves.lumnlibrarypackage.activity.gridlist.GridBasic;
//import org.oves.lumnlibrarypackage.activity.gridlist.GridCaller;
//import org.oves.lumnlibrarypackage.activity.gridlist.GridSectioned;
//import org.oves.lumnlibrarypackage.activity.gridlist.GridSingleLine;
//import org.oves.lumnlibrarypackage.activity.gridlist.GridTwoLine;
//import org.oves.lumnlibrarypackage.activity.list.ListAnimation;
//import org.oves.lumnlibrarypackage.activity.list.ListBasic;
//import org.oves.lumnlibrarypackage.activity.list.ListDrag;
//import org.oves.lumnlibrarypackage.activity.list.ListExpand;
//import org.oves.lumnlibrarypackage.activity.list.ListMultiSelection;
//import org.oves.lumnlibrarypackage.activity.list.ListNewsCard;
//import org.oves.lumnlibrarypackage.activity.list.ListNewsImage;
//import org.oves.lumnlibrarypackage.activity.list.ListNewsLight;
//import org.oves.lumnlibrarypackage.activity.list.ListNewsLightHorizontal;
//import org.oves.lumnlibrarypackage.activity.list.ListSectioned;
//import org.oves.lumnlibrarypackage.activity.list.ListSwipe;
//import org.oves.lumnlibrarypackage.activity.login.LoginBigImage;
//import org.oves.lumnlibrarypackage.activity.login.LoginCardLight;
//import org.oves.lumnlibrarypackage.activity.login.LoginCardOverlap;
//import org.oves.lumnlibrarypackage.activity.login.LoginImageTeal;
//import org.oves.lumnlibrarypackage.activity.login.LoginSimpleDark;
//import org.oves.lumnlibrarypackage.activity.login.LoginSimpleGreen;
//import org.oves.lumnlibrarypackage.activity.login.LoginSimpleLight;
//import org.oves.lumnlibrarypackage.activity.menu.MenuDrawerAdmin;
//import org.oves.lumnlibrarypackage.activity.menu.MenuDrawerAdminDark;
//import org.oves.lumnlibrarypackage.activity.menu.MenuDrawerAgri;
//import org.oves.lumnlibrarypackage.activity.menu.MenuDrawerFilter;
//import org.oves.lumnlibrarypackage.activity.menu.MenuDrawerMail;
//import org.oves.lumnlibrarypackage.activity.menu.MenuDrawerNews;
//import org.oves.lumnlibrarypackage.activity.menu.MenuDrawerNoIcon;
//import org.oves.lumnlibrarypackage.activity.menu.MenuDrawerSimpleDark;
//import org.oves.lumnlibrarypackage.activity.menu.MenuDrawerSimpleLight;
//import org.oves.lumnlibrarypackage.activity.menu.MenuDrawerWhite;
//import org.oves.lumnlibrarypackage.activity.menu.MenuDrawerWhiteProgress;
//import org.oves.lumnlibrarypackage.activity.menu.MenuOverflowList;
//import org.oves.lumnlibrarypackage.activity.menu.MenuOverflowToolbar;
//import org.oves.lumnlibrarypackage.activity.noitem.NoItemArchived;
//import org.oves.lumnlibrarypackage.activity.noitem.NoItemBgCactus;
//import org.oves.lumnlibrarypackage.activity.noitem.NoItemBgCity;
//import org.oves.lumnlibrarypackage.activity.noitem.NoItemInternetIcon;
//import org.oves.lumnlibrarypackage.activity.noitem.NoItemInternetImage;
//import org.oves.lumnlibrarypackage.activity.noitem.NoItemSearch;
//import org.oves.lumnlibrarypackage.activity.noitem.NoItemTabs;
//import org.oves.lumnlibrarypackage.activity.payment.PaymentCardCollections;
//import org.oves.lumnlibrarypackage.activity.payment.PaymentCardDetails;
//import org.oves.lumnlibrarypackage.activity.payment.PaymentForm;
//import org.oves.lumnlibrarypackage.activity.payment.PaymentProfile;
//import org.oves.lumnlibrarypackage.activity.payment.PaymentSuccessDialog;
//import org.oves.lumnlibrarypackage.activity.picker.PickerColor;
//import org.oves.lumnlibrarypackage.activity.picker.PickerDateDark;
//import org.oves.lumnlibrarypackage.activity.picker.PickerDateLight;
//import org.oves.lumnlibrarypackage.activity.picker.PickerLocation;
//import org.oves.lumnlibrarypackage.activity.picker.PickerTimeDark;
//import org.oves.lumnlibrarypackage.activity.picker.PickerTimeLight;
//import org.oves.lumnlibrarypackage.activity.player.PlayerMusicAlbumCircle;
//import org.oves.lumnlibrarypackage.activity.player.PlayerMusicAlbumDark;
//import org.oves.lumnlibrarypackage.activity.player.PlayerMusicAlbumGrid;
//import org.oves.lumnlibrarypackage.activity.player.PlayerMusicAlbumSimple;
//import org.oves.lumnlibrarypackage.activity.player.PlayerMusicBasic;
//import org.oves.lumnlibrarypackage.activity.player.PlayerMusicGenre;
//import org.oves.lumnlibrarypackage.activity.player.PlayerMusicGenreImage;
//import org.oves.lumnlibrarypackage.activity.player.PlayerMusicGenreLight;
//import org.oves.lumnlibrarypackage.activity.player.PlayerMusicLight;
//import org.oves.lumnlibrarypackage.activity.player.PlayerMusicSongList;
//import org.oves.lumnlibrarypackage.activity.player.PlayerMusicTabs;
//import org.oves.lumnlibrarypackage.activity.player.PlayerMusicTabsIcon;
//import org.oves.lumnlibrarypackage.activity.player.PlayerVideoBasic;
//import org.oves.lumnlibrarypackage.activity.player.PlayerVideoSimple;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileBlueAppbar;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileCardHeader;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileCardHeaderImage;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileCardList;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileCardOverlap;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileCircleImage;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileDark;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileDrawerImage;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileDrawerSimple;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileFabMenu;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileFormal;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileFreelancer;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileGallery;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileGalleryTwo;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileImageAppbar;
//import org.oves.lumnlibrarypackage.activity.profile.ProfilePink;
//import org.oves.lumnlibrarypackage.activity.profile.ProfilePolygon;
//import org.oves.lumnlibrarypackage.activity.profile.ProfilePurple;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileRating;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileRed;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileSkills;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileTab;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileWallet;
//import org.oves.lumnlibrarypackage.activity.profile.ProfileWhite;
//import org.oves.lumnlibrarypackage.activity.progressactivity.ProgressBasic;
//import org.oves.lumnlibrarypackage.activity.progressactivity.ProgressCircleCenter;
//import org.oves.lumnlibrarypackage.activity.progressactivity.ProgressDotsBounce;
//import org.oves.lumnlibrarypackage.activity.progressactivity.ProgressDotsFade;
//import org.oves.lumnlibrarypackage.activity.progressactivity.ProgressDotsGrow;
//import org.oves.lumnlibrarypackage.activity.progressactivity.ProgressLinearCenter;
//import org.oves.lumnlibrarypackage.activity.progressactivity.ProgressLinearTop;
//import org.oves.lumnlibrarypackage.activity.progressactivity.ProgressOnScroll;
//import org.oves.lumnlibrarypackage.activity.progressactivity.ProgressPullRefresh;
//import org.oves.lumnlibrarypackage.activity.search.SearchCity;
//import org.oves.lumnlibrarypackage.activity.search.SearchEventType;
//import org.oves.lumnlibrarypackage.activity.search.SearchFilterHotel;
//import org.oves.lumnlibrarypackage.activity.search.SearchFilterProduct;
//import org.oves.lumnlibrarypackage.activity.search.SearchFilterProperty;
//import org.oves.lumnlibrarypackage.activity.search.SearchHistoryCard;
//import org.oves.lumnlibrarypackage.activity.search.SearchOutlet;
//import org.oves.lumnlibrarypackage.activity.search.SearchOutletYellow;
//import org.oves.lumnlibrarypackage.activity.search.SearchPrimary;
//import org.oves.lumnlibrarypackage.activity.search.SearchPrimaryBg;
//import org.oves.lumnlibrarypackage.activity.search.SearchStore;
//import org.oves.lumnlibrarypackage.activity.search.SearchSuggestion;
//import org.oves.lumnlibrarypackage.activity.search.SearchSuggestionRed;
//import org.oves.lumnlibrarypackage.activity.search.SearchToolbarDark;
//import org.oves.lumnlibrarypackage.activity.search.SearchToolbarLight;
//import org.oves.lumnlibrarypackage.activity.settings.SettingFlat;
//import org.oves.lumnlibrarypackage.activity.settings.SettingProfile;
//import org.oves.lumnlibrarypackage.activity.settings.SettingProfileLight;
//import org.oves.lumnlibrarypackage.activity.settings.SettingSectioned;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingCartCard;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingCartCardDark;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingCartSimple;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingCategoryCard;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingCategoryImage;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingCategoryList;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingCheckoutCard;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingCheckoutOnePage;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingCheckoutStep;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingCheckoutTimeline;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingProductAdvDetails;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingProductDetails;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingProductGrid;
//import org.oves.lumnlibrarypackage.activity.shopping.ShoppingSubCategoryTabs;
//import org.oves.lumnlibrarypackage.activity.slider.SliderColorPicker;
//import org.oves.lumnlibrarypackage.activity.slider.SliderDark;
//import org.oves.lumnlibrarypackage.activity.slider.SliderLight;
//import org.oves.lumnlibrarypackage.activity.slider.SliderRange;
//import org.oves.lumnlibrarypackage.activity.sliderimage.SliderImageCard;
//import org.oves.lumnlibrarypackage.activity.sliderimage.SliderImageCardAuto;
//import org.oves.lumnlibrarypackage.activity.sliderimage.SliderImageHeader;
//import org.oves.lumnlibrarypackage.activity.sliderimage.SliderImageHeaderAuto;
//import org.oves.lumnlibrarypackage.activity.sliderimage.SliderSnapBasic;
//import org.oves.lumnlibrarypackage.activity.sliderimage.SliderSnapCard;
//import org.oves.lumnlibrarypackage.activity.sliderimage.SliderSnapFull;
//import org.oves.lumnlibrarypackage.activity.sliderimage.SliderSnapNews;
//import org.oves.lumnlibrarypackage.activity.snackbartoast.SnackbarAndFab;
//import org.oves.lumnlibrarypackage.activity.snackbartoast.SnackbarCustom;
//import org.oves.lumnlibrarypackage.activity.snackbartoast.SnackbarToastBasic;
//import org.oves.lumnlibrarypackage.activity.snackbartoast.ToastCustom;
//import org.oves.lumnlibrarypackage.activity.stepper.StepperDots;
//import org.oves.lumnlibrarypackage.activity.stepper.StepperProgress;
//import org.oves.lumnlibrarypackage.activity.stepper.StepperText;
//import org.oves.lumnlibrarypackage.activity.stepper.StepperVertical;
//import org.oves.lumnlibrarypackage.activity.stepper.StepperWizardColor;
//import org.oves.lumnlibrarypackage.activity.stepper.StepperWizardLight;
//import org.oves.lumnlibrarypackage.activity.tabs.TabsBasic;
//import org.oves.lumnlibrarypackage.activity.tabs.TabsDark;
//import org.oves.lumnlibrarypackage.activity.tabs.TabsIcon;
//import org.oves.lumnlibrarypackage.activity.tabs.TabsIconLight;
//import org.oves.lumnlibrarypackage.activity.tabs.TabsIconStack;
//import org.oves.lumnlibrarypackage.activity.tabs.TabsLight;
//import org.oves.lumnlibrarypackage.activity.tabs.TabsRound;
//import org.oves.lumnlibrarypackage.activity.tabs.TabsScroll;
//import org.oves.lumnlibrarypackage.activity.tabs.TabsStore;
//import org.oves.lumnlibrarypackage.activity.tabs.TabsTextIcon;
//import org.oves.lumnlibrarypackage.activity.timeline.TimelineCard;
//import org.oves.lumnlibrarypackage.activity.timeline.TimelineDotCard;
//import org.oves.lumnlibrarypackage.activity.timeline.TimelineExplore;
//import org.oves.lumnlibrarypackage.activity.timeline.TimelineFeed;
//import org.oves.lumnlibrarypackage.activity.timeline.TimelineImageFit;
//import org.oves.lumnlibrarypackage.activity.timeline.TimelineImageMix;
//import org.oves.lumnlibrarypackage.activity.timeline.TimelinePath;
//import org.oves.lumnlibrarypackage.activity.timeline.TimelineSimple;
//import org.oves.lumnlibrarypackage.activity.timeline.TimelineTwitter;
//import org.oves.lumnlibrarypackage.activity.toolbar.ToolbarBasic;
//import org.oves.lumnlibrarypackage.activity.toolbar.ToolbarBottomBasic;
//import org.oves.lumnlibrarypackage.activity.toolbar.ToolbarBottomFab;
//import org.oves.lumnlibrarypackage.activity.toolbar.ToolbarBottomScroll;
//import org.oves.lumnlibrarypackage.activity.toolbar.ToolbarCollapse;
//import org.oves.lumnlibrarypackage.activity.toolbar.ToolbarCollapsePin;
//import org.oves.lumnlibrarypackage.activity.toolbar.ToolbarDark;
//import org.oves.lumnlibrarypackage.activity.toolbar.ToolbarLight;
//import org.oves.lumnlibrarypackage.activity.verification.VerificationBlue;
//import org.oves.lumnlibrarypackage.activity.verification.VerificationCode;
//import org.oves.lumnlibrarypackage.activity.verification.VerificationHeader;
//import org.oves.lumnlibrarypackage.activity.verification.VerificationImage;
//import org.oves.lumnlibrarypackage.activity.verification.VerificationOrange;
//import org.oves.lumnlibrarypackage.activity.verification.VerificationPhone;

public class MainMenu extends AppCompatActivity {

    public static boolean active = false;
    private final int notification_count = -1;
    List<Item> search_items = new ArrayList<>();
    private RecyclerView recycler, search_recycler;
    private MnAdapter adapter;
    private MnSearchAdapter searchAdapter;
    private SharedPref sharedPref;
    private ActionBar actionBar;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private View navigation_header;
    private NestedScrollView nested_scrollview;
    private View notif_badge;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = new SharedPref(this);

        initToolbar();
//        initComponentMenu();
//        initDrawerMenu();

        moPubInitBanner();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        Tools.setSystemBarColorInt(this, Color.parseColor("#0A0A0A"));
    }

//    private void initComponentMenu() {
//        nested_scrollview = findViewById(R.id.nested_scrollview);
//        recycler = findViewById(R.id.main_recycler);
//        adapter = new MnAdapter(this, generateMenuItems(), new MnAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, Item item) {
//                onMenuItemSelected(item);
//            }
//        });
//
//        adapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
//        recycler.setLayoutManager(new LinearLayoutManager(this));
//        recycler.setNestedScrollingEnabled(false);
//        recycler.setAdapter(adapter);
//
//        search_recycler = findViewById(R.id.search_recycler);
//        search_recycler.setLayoutManager(new LinearLayoutManager(this));
//        search_recycler.setNestedScrollingEnabled(false);
//        searchAdapter = new MnSearchAdapter(this, search_items);
//        search_recycler.setAdapter(searchAdapter);
//        searchAdapter.setOnItemClickListener(new MnSearchAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, Item obj, int position) {
//                onMenuItemSelected(obj);
//            }
//        });
//
//        if (sharedPref.isFirstLaunch()) {
//            showDialogAbout();
//        }
//    }

    private void onMenuItemSelected(Item item) {

        if (sharedPref.getClickSwitch()) {
            if (sharedPref.actionClickOffer()) {
                showDialogOffer();
                sharedPref.setClickSwitch(false);
                return;
            }
        } else {
            if (sharedPref.actionClickInters()) {
                boolean istShown = showInterstitial();
                sharedPref.setClickSwitch(true);
                if (istShown) return;
            }
        }

        if (item.Act != null) {
            startActivity(new Intent(this, item.Act));
            return;
        }

        switch (item.Id) {
            case 1:
//                showDialogAbout();
                break;
        }
    }

//    private List<Item> generateMenuItems() {
//        List<Item> items = new ArrayList<>();
//
//        items.add(new Item(-1, null, MnType.DIV));
//        Item bn = new Item(100, "Bottom Navigation", R.drawable.ic_view_column, MnType.HEAD);
//        items.add(bn);
//        items.add(new Item(101, bn.Text, "Basic", MnType.SUB, BottomNavigationBasic.class));
//        items.add(new Item(102, bn.Text, "Shifting", MnType.SUB, BottomNavigationShifting.class));
//        items.add(new Item(103, bn.Text, "Light", MnType.SUB, BottomNavigationLight.class));
//        items.add(new Item(104, bn.Text, "Dark", MnType.SUB, BottomNavigationDark.class));
//        items.add(new Item(105, bn.Text, "Icon", MnType.SUB, BottomNavigationIcon.class));
//        items.add(new Item(106, bn.Text, "Primary", MnType.SUB, BottomNavigationPrimary.class));
//        items.add(new Item(107, bn.Text, "Map Blue", MnType.SUB, BottomNavigationMapBlue.class));
//        items.add(new Item(108, bn.Text, "Light Simple", MnType.SUB, BottomNavigationLightSimple.class));
//        items.add(new Item(108, bn.Text, "Article", MnType.SUB, BottomNavigationArticle.class));
//        items.add(new Item(108, bn.Text, "Shop", MnType.SUB, BottomNavigationShop.class));
//        items.add(new Item(108, bn.Text, "Small", MnType.SUB, BottomNavigationSmall.class));
//        items.add(new Item(108, bn.Text, "Main", MnType.SUB, BottomNavigationMain.class));
//
//        Item bs = new Item(200, "Bottom Sheet", R.drawable.ic_call_to_actio, true, MnType.HEAD);
//        items.add(bs);
//        items.add(new Item(201, bs.Text, "Basic", MnType.SUB, BottomSheetBasic.class));
//        items.add(new Item(202, bs.Text, "List", MnType.SUB, BottomSheetList.class));
//        items.add(new Item(203, bs.Text, "Map", MnType.SUB, BottomSheetMap.class));
//        items.add(new Item(204, bs.Text, "Floating", MnType.SUB, BottomSheetFloating.class));
//        items.add(new Item(205, bs.Text, "Full", MnType.SUB, BottomSheetFull.class));
//        items.add(new Item(206, bs.Text, "Filter", true, MnType.SUB, BottomSheetFilter.class));
//        items.add(new Item(207, bs.Text, "Menu", true, MnType.SUB, BottomSheetMenu.class));
//
//        Item bt = new Item(300, "Buttons", R.drawable.ic_touch_app, true, MnType.HEAD);
//        items.add(bt);
//        items.add(new Item(301, bt.Text, "Basic", MnType.SUB, ButtonBasic.class));
//        items.add(new Item(302, bt.Text, "Button In Utilities", MnType.SUB, ButtonInUtilities.class));
//        items.add(new Item(303, bt.Text, "Fab Middle", MnType.SUB, FabMiddle.class));
//        items.add(new Item(304, bt.Text, "Fab More", MnType.SUB, FabMore.class));
//        items.add(new Item(305, bt.Text, "Fab More Text", MnType.SUB, FabMoreText.class));
//        items.add(new Item(306, bt.Text, "Toggle Basic", true, MnType.SUB, ButtonToggleBasic.class));
//        items.add(new Item(307, bt.Text, "Fab Extend", true, MnType.SUB, FabExtend.class));
//
//        Item crd = new Item(400, "Cards", R.drawable.ic_note, true, MnType.HEAD);
//        items.add(crd);
//        items.add(new Item(401, crd.Text, "Basic", MnType.SUB, CardBasic.class));
//        items.add(new Item(402, crd.Text, "Timeline", MnType.SUB, CardTimeline.class));
//        items.add(new Item(403, crd.Text, "Overlap", MnType.SUB, CardOverlap.class));
//        items.add(new Item(404, crd.Text, "Wizard", MnType.SUB, CardWizard.class));
//        items.add(new Item(405, crd.Text, "Wizard Light", MnType.SUB, CardWizardLight.class));
//        items.add(new Item(406, crd.Text, "Wizard Overlap", MnType.SUB, CardWizardOverlap.class));
//        items.add(new Item(407, crd.Text, "Checkable", true, MnType.SUB, CardCheckable.class));
//
//        Item chp = new Item(500, "Chips", R.drawable.ic_label, MnType.HEAD);
//        items.add(chp);
//        items.add(new Item(501, chp.Text, "Basic", MnType.SUB, ChipBasic.class));
//        items.add(new Item(502, chp.Text, "Tag", MnType.SUB, ChipTag.class));
//
//        Item dlg = new Item(600, "Dialogs", R.drawable.ic_picture_in_picture, MnType.HEAD);
//        items.add(dlg);
//        items.add(new Item(601, dlg.Text, "Basic", MnType.SUB, DialogBasic.class));
//        items.add(new Item(602, dlg.Text, "Fullscreen", MnType.SUB, DialogFullscreen.class));
//        items.add(new Item(603, dlg.Text, "Custom", MnType.SUB, DialogCustom.class));
//        items.add(new Item(604, dlg.Text, "Custom Info", MnType.SUB, DialogCustomInfo.class));
//        items.add(new Item(605, dlg.Text, "Custom Warning", MnType.SUB, DialogCustomWarning.class));
//        items.add(new Item(606, dlg.Text, "Custom Light", MnType.SUB, DialogCustomLight.class));
//        items.add(new Item(607, dlg.Text, "Custom Dark", MnType.SUB, DialogCustomDark.class));
//        items.add(new Item(608, dlg.Text, "Custom Add Post", MnType.SUB, DialogAddPost.class));
//        items.add(new Item(609, dlg.Text, "Custom Add Review", MnType.SUB, DialogAddReview.class));
//        items.add(new Item(610, dlg.Text, "GDPR Basic", MnType.SUB, DialogGDPRBasic.class));
//        items.add(new Item(611, dlg.Text, "Term of Services", MnType.SUB, DialogTermOfServices.class));
//        items.add(new Item(612, dlg.Text, "Header", MnType.SUB, DialogHeader.class));
//        items.add(new Item(613, dlg.Text, "Image", MnType.SUB, DialogImage.class));
//        items.add(new Item(614, dlg.Text, "Menu Payment", MnType.SUB, DialogMenuPayment.class));
//        items.add(new Item(615, dlg.Text, "Achievement", MnType.SUB, DialogAchievement.class));
//        items.add(new Item(616, dlg.Text, "Contact Us", MnType.SUB, DialogContactUs.class));
//
//        Item exp = new Item(700, "Expansion Panels", R.drawable.ic_arrow_downward, MnType.HEAD);
//        items.add(exp);
//        items.add(new Item(701, exp.Text, "Basic", MnType.SUB, ExpansionPanelBasic.class));
//        items.add(new Item(702, exp.Text, "Invoice", MnType.SUB, ExpansionPanelInvoice.class));
//        items.add(new Item(703, exp.Text, "Ticket", MnType.SUB, ExpansionPanelTicket.class));
//
//        Item grd = new Item(800, "Grid", R.drawable.ic_apps, MnType.HEAD);
//        items.add(grd);
//        items.add(new Item(801, grd.Text, "Basic", MnType.SUB, GridBasic.class));
//        items.add(new Item(802, grd.Text, "Single Line", MnType.SUB, GridSingleLine.class));
//        items.add(new Item(803, grd.Text, "Two Line", MnType.SUB, GridTwoLine.class));
//        items.add(new Item(804, grd.Text, "Sectioned", MnType.SUB, GridSectioned.class));
//        items.add(new Item(805, grd.Text, "Albums", MnType.SUB, GridAlbums.class));
//        items.add(new Item(806, grd.Text, "Caller", MnType.SUB, GridCaller.class));
//
//        Item lst = new Item(900, "Lists", R.drawable.ic_view_stream, MnType.HEAD);
//        items.add(lst);
//        items.add(new Item(901, lst.Text, "Basic", MnType.SUB, ListBasic.class));
//        items.add(new Item(902, lst.Text, "Sectioned", MnType.SUB, ListSectioned.class));
//        items.add(new Item(903, lst.Text, "Animation", MnType.SUB, ListAnimation.class));
//        items.add(new Item(904, lst.Text, "Expand", MnType.SUB, ListExpand.class));
//        items.add(new Item(905, lst.Text, "Draggable", MnType.SUB, ListDrag.class));
//        items.add(new Item(906, lst.Text, "Swipe", MnType.SUB, ListSwipe.class));
//        items.add(new Item(907, lst.Text, "Multi Selection", MnType.SUB, ListMultiSelection.class));
//        items.add(new Item(908, lst.Text, "News Light", MnType.SUB, ListNewsLight.class));
//        items.add(new Item(909, lst.Text, "News Light Horizontal", MnType.SUB, ListNewsLightHorizontal.class));
//        items.add(new Item(910, lst.Text, "News Card", MnType.SUB, ListNewsCard.class));
//        items.add(new Item(911, lst.Text, "News Image", MnType.SUB, ListNewsImage.class));
//
//        Item mn = new Item(2000, "Menu", R.drawable.ic_reoder, MnType.HEAD);
//        items.add(mn);
//        items.add(new Item(2001, mn.Text, "Drawer News", MnType.SUB, MenuDrawerNews.class));
//        items.add(new Item(2002, mn.Text, "Drawer Mail", MnType.SUB, MenuDrawerMail.class));
//        items.add(new Item(2003, mn.Text, "Drawer Simple Light", MnType.SUB, MenuDrawerSimpleLight.class));
//        items.add(new Item(2004, mn.Text, "Drawer Simple Dark", MnType.SUB, MenuDrawerSimpleDark.class));
//        items.add(new Item(2005, mn.Text, "Drawer No Icon", MnType.SUB, MenuDrawerNoIcon.class));
//        items.add(new Item(2006, mn.Text, "Overflow Toolbar", MnType.SUB, MenuOverflowToolbar.class));
//        items.add(new Item(2007, mn.Text, "Overflow List", MnType.SUB, MenuOverflowList.class));
//        items.add(new Item(2008, mn.Text, "Drawer White", MnType.SUB, MenuDrawerWhite.class));
//        items.add(new Item(2009, mn.Text, "Drawer White Progress", MnType.SUB, MenuDrawerWhiteProgress.class));
//        items.add(new Item(2010, mn.Text, "Drawer Agri", MnType.SUB, MenuDrawerAgri.class));
//        items.add(new Item(2011, mn.Text, "Drawer Filter", MnType.SUB, MenuDrawerFilter.class));
//        items.add(new Item(2012, mn.Text, "Drawer Admin", MnType.SUB, MenuDrawerAdmin.class));
//        items.add(new Item(2013, mn.Text, "Drawer Admin Dark", MnType.SUB, MenuDrawerAdminDark.class));
//
//        Item pck = new Item(1000, "Pickers", R.drawable.ic_event, MnType.HEAD);
//        items.add(pck);
//        items.add(new Item(1001, pck.Text, "Date Light", MnType.SUB, PickerDateLight.class));
//        items.add(new Item(1002, pck.Text, "Date Dark", MnType.SUB, PickerDateDark.class));
//        items.add(new Item(1003, pck.Text, "Time Light", MnType.SUB, PickerTimeLight.class));
//        items.add(new Item(1004, pck.Text, "Time Dark", MnType.SUB, PickerTimeDark.class));
//        items.add(new Item(1005, pck.Text, "Color RGB", MnType.SUB, PickerColor.class));
//        items.add(new Item(1006, pck.Text, "Location", MnType.SUB, PickerLocation.class));
//
//        Item pa = new Item(1100, "Progress & Activity", R.drawable.ic_settings_backup_restore, MnType.HEAD);
//        items.add(pa);
//        items.add(new Item(1101, pa.Text, "Basic", MnType.SUB, ProgressBasic.class));
//        items.add(new Item(1102, pa.Text, "Linear Center", MnType.SUB, ProgressLinearCenter.class));
//        items.add(new Item(1103, pa.Text, "Linear Top", MnType.SUB, ProgressLinearTop.class));
//        items.add(new Item(1104, pa.Text, "Circle Center", MnType.SUB, ProgressCircleCenter.class));
//        items.add(new Item(1105, pa.Text, "On Scroll", MnType.SUB, ProgressOnScroll.class));
//        items.add(new Item(1106, pa.Text, "Pull Refresh", MnType.SUB, ProgressPullRefresh.class));
//        items.add(new Item(1107, pa.Text, "Dots Bounce", MnType.SUB, ProgressDotsBounce.class));
//        items.add(new Item(1108, pa.Text, "Dots Fade", MnType.SUB, ProgressDotsFade.class));
//        items.add(new Item(1109, pa.Text, "Dots Grow", MnType.SUB, ProgressDotsGrow.class));
//
//        Item sld = new Item(1200, "Sliders", R.drawable.ic_tune, true, MnType.HEAD);
//        items.add(sld);
//        items.add(new Item(1201, sld.Text, "Light", MnType.SUB, SliderLight.class));
//        items.add(new Item(1202, sld.Text, "Dark", MnType.SUB, SliderDark.class));
//        items.add(new Item(1203, sld.Text, "Color Picker", MnType.SUB, SliderColorPicker.class));
//        items.add(new Item(1204, sld.Text, "Range", true, MnType.SUB, SliderRange.class));
//
//        Item st = new Item(1300, "Snackbars & Toasts", R.drawable.ic_wb_iridescent, MnType.HEAD);
//        items.add(st);
//        items.add(new Item(1301, st.Text, "Basic", MnType.SUB, SnackbarToastBasic.class));
//        items.add(new Item(1302, st.Text, "Lift FAB", MnType.SUB, SnackbarAndFab.class));
//        items.add(new Item(1303, st.Text, "Toast Custom", MnType.SUB, ToastCustom.class));
//        items.add(new Item(1304, st.Text, "Snackbar Custom", MnType.SUB, SnackbarCustom.class));
//
//        Item stp = new Item(1400, "Steppers", R.drawable.ic_timeline, MnType.HEAD);
//        items.add(stp);
//        items.add(new Item(1401, stp.Text, "Text", MnType.SUB, StepperText.class));
//        items.add(new Item(1402, stp.Text, "Dots", MnType.SUB, StepperDots.class));
//        items.add(new Item(1403, stp.Text, "Progress", MnType.SUB, StepperProgress.class));
//        items.add(new Item(1404, stp.Text, "Vertical", MnType.SUB, StepperVertical.class));
//        items.add(new Item(1405, stp.Text, "Wizard Light", MnType.SUB, StepperWizardLight.class));
//        items.add(new Item(1406, stp.Text, "Wizard Color", MnType.SUB, StepperWizardColor.class));
//
//        Item tab = new Item(1500, "Tabs", R.drawable.ic_tabs, MnType.HEAD);
//        items.add(tab);
//        items.add(new Item(1501, tab.Text, "Basic", MnType.SUB, TabsBasic.class));
//        items.add(new Item(1502, tab.Text, "Store", MnType.SUB, TabsStore.class));
//        items.add(new Item(1503, tab.Text, "Light", MnType.SUB, TabsLight.class));
//        items.add(new Item(1504, tab.Text, "Dark", MnType.SUB, TabsDark.class));
//        items.add(new Item(1505, tab.Text, "Icon", MnType.SUB, TabsIcon.class));
//        items.add(new Item(1506, tab.Text, "Text & Icon", MnType.SUB, TabsTextIcon.class));
//        items.add(new Item(1507, tab.Text, "Icon Light", MnType.SUB, TabsIconLight.class));
//        items.add(new Item(1508, tab.Text, "Icon Stack", MnType.SUB, TabsIconStack.class));
//        items.add(new Item(1509, tab.Text, "Scroll", MnType.SUB, TabsScroll.class));
//        items.add(new Item(1510, tab.Text, "Round", MnType.SUB, TabsRound.class));
//
//        Item frm = new Item(1600, "Form", R.drawable.ic_assignment, MnType.HEAD);
//        items.add(frm);
//        items.add(new Item(1601, frm.Text, "Login", MnType.SUB, FormLogin.class));
//        items.add(new Item(1602, frm.Text, "Sign Up", MnType.SUB, FormSignUp.class));
//        items.add(new Item(1603, frm.Text, "Profile Data", MnType.SUB, FormProfileData.class));
//        items.add(new Item(1604, frm.Text, "With Icon", MnType.SUB, FormWithIcon.class));
//        items.add(new Item(1605, frm.Text, "Text Area", MnType.SUB, FormTextArea.class));
//        items.add(new Item(1606, frm.Text, "Address", MnType.SUB, FormAddress.class));
//        items.add(new Item(1607, frm.Text, "Checkout", MnType.SUB, FormCheckout.class));
//        items.add(new Item(1608, frm.Text, "Ecommerce", MnType.SUB, FormEcommerce.class));
//        items.add(new Item(1609, frm.Text, "Sign Up Card", MnType.SUB, FormSignupCard.class));
//        items.add(new Item(1610, frm.Text, "Sign Up Card Stack", MnType.SUB, FormSignupCardStack.class));
//        items.add(new Item(1611, frm.Text, "Sign Up Dark", MnType.SUB, FormSignupDark.class));
//        items.add(new Item(1612, frm.Text, "Sign Up Image", MnType.SUB, FormSignupImage.class));
//        items.add(new Item(1613, frm.Text, "Sign Up Image Card", MnType.SUB, FormSignupImageCard.class));
//        items.add(new Item(1614, frm.Text, "Sign Up Image Outline", MnType.SUB, FormSignupImageOutline.class));
//        items.add(new Item(1615, frm.Text, "Input Outline", MnType.SUB, FormInputOutlined.class));
//        items.add(new Item(1617, frm.Text, "Add Project", MnType.SUB, FormAddProject.class));
//        items.add(new Item(1618, frm.Text, "Add Project Blue", MnType.SUB, FormAddProjectBlue.class));
//
//        Item tlb = new Item(1700, "Toolbars", R.drawable.ic_web_asset, true, MnType.HEAD);
//        items.add(tlb);
//        items.add(new Item(1701, tlb.Text, "Basic", MnType.SUB, ToolbarBasic.class));
//        items.add(new Item(1702, tlb.Text, "Collapse", MnType.SUB, ToolbarCollapse.class));
//        items.add(new Item(1703, tlb.Text, "Collapse And Pin", MnType.SUB, ToolbarCollapsePin.class));
//        items.add(new Item(1704, tlb.Text, "Light", MnType.SUB, ToolbarLight.class));
//        items.add(new Item(1705, tlb.Text, "Dark", MnType.SUB, ToolbarDark.class));
//        items.add(new Item(1706, tlb.Text, "Bottom Basic", true, MnType.SUB, ToolbarBottomBasic.class));
//        items.add(new Item(1707, tlb.Text, "Bottom Fab", true, MnType.SUB, ToolbarBottomFab.class));
//        items.add(new Item(1708, tlb.Text, "Bottom Scroll", true, MnType.SUB, ToolbarBottomScroll.class));
//
//        items.add(new Item(-1, "Extra", MnType.DIV));
//
//        Item pfl = new Item(1800, "Profile", R.drawable.ic_person, MnType.HEAD);
//        items.add(pfl);
//        items.add(new Item(1801, pfl.Text, "Polygon", MnType.SUB, ProfilePolygon.class));
//        items.add(new Item(1802, pfl.Text, "Purple", MnType.SUB, ProfilePurple.class));
//        items.add(new Item(1803, pfl.Text, "Red", MnType.SUB, ProfileRed.class));
//        items.add(new Item(1804, pfl.Text, "Blue Appbar", MnType.SUB, ProfileBlueAppbar.class));
//        items.add(new Item(1805, pfl.Text, "Image Appbar", MnType.SUB, ProfileImageAppbar.class));
//        items.add(new Item(1806, pfl.Text, "Drawer Simple", MnType.SUB, ProfileDrawerSimple.class));
//        items.add(new Item(1807, pfl.Text, "Drawer Image", MnType.SUB, ProfileDrawerImage.class));
//        items.add(new Item(1808, pfl.Text, "Gallery", MnType.SUB, ProfileGallery.class));
//        items.add(new Item(1809, pfl.Text, "Gallery Two", MnType.SUB, ProfileGalleryTwo.class));
//        items.add(new Item(1810, pfl.Text, "Card List", MnType.SUB, ProfileCardList.class));
//        items.add(new Item(1811, pfl.Text, "Fab Menu", MnType.SUB, ProfileFabMenu.class));
//        items.add(new Item(1812, pfl.Text, "Card Header", MnType.SUB, ProfileCardHeader.class));
//        items.add(new Item(1813, pfl.Text, "Card Header Image", MnType.SUB, ProfileCardHeaderImage.class));
//        items.add(new Item(1814, pfl.Text, "Card Overlap", MnType.SUB, ProfileCardOverlap.class));
//        items.add(new Item(1815, pfl.Text, "Formal", MnType.SUB, ProfileFormal.class));
//        items.add(new Item(1816, pfl.Text, "Freelancer", MnType.SUB, ProfileFreelancer.class));
//        items.add(new Item(1817, pfl.Text, "Rating", MnType.SUB, ProfileRating.class));
//        items.add(new Item(1818, pfl.Text, "Skills", MnType.SUB, ProfileSkills.class));
//        items.add(new Item(1819, pfl.Text, "Wallet", MnType.SUB, ProfileWallet.class));
//        items.add(new Item(1820, pfl.Text, "Tab", MnType.SUB, ProfileTab.class));
//        items.add(new Item(1821, pfl.Text, "Pink", MnType.SUB, ProfilePink.class));
//        items.add(new Item(1822, pfl.Text, "White", MnType.SUB, ProfileWhite.class));
//        items.add(new Item(1823, pfl.Text, "Dark", MnType.SUB, ProfileDark.class));
//        items.add(new Item(1824, pfl.Text, "Circle Image", MnType.SUB, ProfileCircleImage.class));
//
//        Item noi = new Item(19000, "No Item Page", R.drawable.ic_do_not_disturb_off, MnType.HEAD);
//        items.add(noi);
//        items.add(new Item(19001, noi.Text, "Archived", MnType.SUB, NoItemArchived.class));
//        items.add(new Item(19002, noi.Text, "Search", MnType.SUB, NoItemSearch.class));
//        items.add(new Item(19003, noi.Text, "Internet Icon", MnType.SUB, NoItemInternetIcon.class));
//        items.add(new Item(19004, noi.Text, "Internet Image", MnType.SUB, NoItemInternetImage.class));
//        items.add(new Item(19005, noi.Text, "Bg City", MnType.SUB, NoItemBgCity.class));
//        items.add(new Item(19006, noi.Text, "Bg Cactus", MnType.SUB, NoItemBgCactus.class));
//        items.add(new Item(19007, noi.Text, "Tabs", MnType.SUB, NoItemTabs.class));
//
//        Item ply = new Item(20000, "Player", R.drawable.ic_live_tv, MnType.HEAD);
//        items.add(ply);
//        items.add(new Item(20001, ply.Text, "Music Basic", MnType.SUB, PlayerMusicBasic.class));
//        items.add(new Item(20002, ply.Text, "Music Light", MnType.SUB, PlayerMusicLight.class));
//        items.add(new Item(20003, ply.Text, "Music Album Dark", MnType.SUB, PlayerMusicAlbumDark.class));
//        items.add(new Item(20004, ply.Text, "Music Album Circle", MnType.SUB, PlayerMusicAlbumCircle.class));
//        items.add(new Item(20005, ply.Text, "Music Album Simple", MnType.SUB, PlayerMusicAlbumSimple.class));
//        items.add(new Item(20006, ply.Text, "Music Song List", MnType.SUB, PlayerMusicSongList.class));
//        items.add(new Item(20007, ply.Text, "Music Album Grid", MnType.SUB, PlayerMusicAlbumGrid.class));
//        items.add(new Item(20008, ply.Text, "Music Tabs", MnType.SUB, PlayerMusicTabs.class));
//        items.add(new Item(20009, ply.Text, "Music Tabs Icon", MnType.SUB, PlayerMusicTabsIcon.class));
//        items.add(new Item(20010, ply.Text, "Music Genre", MnType.SUB, PlayerMusicGenre.class));
//        items.add(new Item(20011, ply.Text, "Music Genre Image", MnType.SUB, PlayerMusicGenreImage.class));
//        items.add(new Item(20012, ply.Text, "Music Genre Light", MnType.SUB, PlayerMusicGenreLight.class));
//        items.add(new Item(20013, ply.Text, "Video Basic", MnType.SUB, PlayerVideoBasic.class));
//        items.add(new Item(20014, ply.Text, "Video Simple", MnType.SUB, PlayerVideoSimple.class));
//
//        Item tl = new Item(21000, "Timeline", R.drawable.ic_wrap_text, MnType.HEAD);
//        items.add(tl);
//        items.add(new Item(21001, tl.Text, "Timeline Feed", MnType.SUB, TimelineFeed.class));
//        items.add(new Item(21002, tl.Text, "Timeline Path", MnType.SUB, TimelinePath.class));
//        items.add(new Item(21003, tl.Text, "Timeline Dot Card", MnType.SUB, TimelineDotCard.class));
//        items.add(new Item(21004, tl.Text, "Timeline Twitter", MnType.SUB, TimelineTwitter.class));
//        items.add(new Item(21005, tl.Text, "Timeline Simple", MnType.SUB, TimelineSimple.class));
//        items.add(new Item(21006, tl.Text, "Timeline Explore", MnType.SUB, TimelineExplore.class));
//        items.add(new Item(21007, tl.Text, "Timeline Image Mix", MnType.SUB, TimelineImageMix.class));
//        items.add(new Item(21008, tl.Text, "Timeline Image Fit", MnType.SUB, TimelineImageFit.class));
//        items.add(new Item(21009, tl.Text, "Timeline Card", MnType.SUB, TimelineCard.class));
//
//        Item shp = new Item(22000, "Shopping", R.drawable.ic_shopping_cart, MnType.HEAD);
//        items.add(shp);
//        items.add(new Item(22001, shp.Text, "Category List", MnType.SUB, ShoppingCategoryList.class));
//        items.add(new Item(22002, shp.Text, "Category Card", MnType.SUB, ShoppingCategoryCard.class));
//        items.add(new Item(22003, shp.Text, "Category Image", MnType.SUB, ShoppingCategoryImage.class));
//        items.add(new Item(22004, shp.Text, "Sub Category Tabs", MnType.SUB, ShoppingSubCategoryTabs.class));
//        items.add(new Item(22005, shp.Text, "Product Grid", MnType.SUB, ShoppingProductGrid.class));
//        items.add(new Item(22006, shp.Text, "Product Details", MnType.SUB, ShoppingProductDetails.class));
//        items.add(new Item(22007, shp.Text, "Product Adv Details", MnType.SUB, ShoppingProductAdvDetails.class));
//        items.add(new Item(22008, shp.Text, "Checkout Card", MnType.SUB, ShoppingCheckoutCard.class));
//        items.add(new Item(22009, shp.Text, "Checkout Step", MnType.SUB, ShoppingCheckoutStep.class));
//        items.add(new Item(22010, shp.Text, "Checkout One Page", MnType.SUB, ShoppingCheckoutOnePage.class));
//        items.add(new Item(22011, shp.Text, "Checkout Timeline", MnType.SUB, ShoppingCheckoutTimeline.class));
//        items.add(new Item(22012, shp.Text, "Cart Simple", MnType.SUB, ShoppingCartSimple.class));
//        items.add(new Item(22013, shp.Text, "Cart Card", MnType.SUB, ShoppingCartCard.class));
//        items.add(new Item(22014, shp.Text, "Cart Dark", MnType.SUB, ShoppingCartCardDark.class));
//
//        Item sp = new Item(23000, "Search Page", R.drawable.ic_search, MnType.HEAD);
//        items.add(sp);
//        items.add(new Item(23001, sp.Text, "Toolbar Light", MnType.SUB, SearchToolbarLight.class));
//        items.add(new Item(23002, sp.Text, "Toolbar Dark", MnType.SUB, SearchToolbarDark.class));
//        items.add(new Item(23003, sp.Text, "Store", MnType.SUB, SearchStore.class));
//        items.add(new Item(23004, sp.Text, "Primary", MnType.SUB, SearchPrimary.class));
//        items.add(new Item(23005, sp.Text, "Primary Bg", MnType.SUB, SearchPrimaryBg.class));
//        items.add(new Item(23006, sp.Text, "History Card", MnType.SUB, SearchHistoryCard.class));
//        items.add(new Item(23007, sp.Text, "City", MnType.SUB, SearchCity.class));
//        items.add(new Item(23008, sp.Text, "Filter Hotel", MnType.SUB, SearchFilterHotel.class));
//        items.add(new Item(23009, sp.Text, "Filter Product", MnType.SUB, SearchFilterProduct.class));
//        items.add(new Item(23010, sp.Text, "Filter Property", MnType.SUB, SearchFilterProperty.class));
//        items.add(new Item(23011, sp.Text, "Event Type", MnType.SUB, SearchEventType.class));
//        items.add(new Item(23012, sp.Text, "Suggestion", MnType.SUB, SearchSuggestion.class));
//        items.add(new Item(23013, sp.Text, "Suggestion Red", MnType.SUB, SearchSuggestionRed.class));
//        items.add(new Item(23014, sp.Text, "Outlet", MnType.SUB, SearchOutlet.class));
//        items.add(new Item(23015, sp.Text, "Outlet Yellow", MnType.SUB, SearchOutletYellow.class));
//
//        Item sldi = new Item(24000, "Slider Image", R.drawable.ic_photo_library, MnType.HEAD);
//        items.add(sldi);
//        items.add(new Item(24001, sldi.Text, "Header", MnType.SUB, SliderImageHeader.class));
//        items.add(new Item(24002, sldi.Text, "Header Auto", MnType.SUB, SliderImageHeaderAuto.class));
//        items.add(new Item(24003, sldi.Text, "Card", MnType.SUB, SliderImageCard.class));
//        items.add(new Item(24004, sldi.Text, "Card Auto", MnType.SUB, SliderImageCardAuto.class));
//        items.add(new Item(24005, sldi.Text, "Snap Basic", MnType.SUB, SliderSnapBasic.class));
//        items.add(new Item(24006, sldi.Text, "Snap Card", MnType.SUB, SliderSnapCard.class));
//        items.add(new Item(24007, sldi.Text, "Snap Full", MnType.SUB, SliderSnapFull.class));
//        items.add(new Item(24008, sldi.Text, "Snap News", MnType.SUB, SliderSnapNews.class));
//
//        Item stg = new Item(25000, "Settings", R.drawable.ic_settings, MnType.HEAD);
//        items.add(stg);
//        items.add(new Item(25001, stg.Text, "Sectioned", MnType.SUB, SettingSectioned.class));
//        items.add(new Item(25002, stg.Text, "Flat", MnType.SUB, SettingFlat.class));
//        items.add(new Item(25003, stg.Text, "Profile", MnType.SUB, SettingProfile.class));
//        items.add(new Item(25004, stg.Text, "Profile Light", MnType.SUB, SettingProfileLight.class));
//
//        Item vrf = new Item(26000, "Verification", R.drawable.ic_check_circle, MnType.HEAD);
//        items.add(vrf);
//        items.add(new Item(26001, vrf.Text, "Phone", MnType.SUB, VerificationPhone.class));
//        items.add(new Item(26002, vrf.Text, "Code", MnType.SUB, VerificationCode.class));
//        items.add(new Item(26003, vrf.Text, "Header", MnType.SUB, VerificationHeader.class));
//        items.add(new Item(26004, vrf.Text, "Image", MnType.SUB, VerificationImage.class));
//        items.add(new Item(26005, vrf.Text, "Blue", MnType.SUB, VerificationBlue.class));
//        items.add(new Item(26006, vrf.Text, "Orange", MnType.SUB, VerificationOrange.class));
//
//        Item lg = new Item(27000, "Login", R.drawable.ic_https, MnType.HEAD);
//        items.add(lg);
//        items.add(new Item(27001, lg.Text, "Simple Light", MnType.SUB, LoginSimpleLight.class));
//        items.add(new Item(27002, lg.Text, "Simple Dark", MnType.SUB, LoginSimpleDark.class));
//        items.add(new Item(27003, lg.Text, "Simple Green", MnType.SUB, LoginSimpleGreen.class));
//        items.add(new Item(27004, lg.Text, "Image Teal", MnType.SUB, LoginImageTeal.class));
//        items.add(new Item(27005, lg.Text, "Card Light", MnType.SUB, LoginCardLight.class));
//        items.add(new Item(27006, lg.Text, "Card Overlap", MnType.SUB, LoginCardOverlap.class));
//        items.add(new Item(27007, lg.Text, "Big Image", MnType.SUB, LoginBigImage.class));
//
//        Item pym = new Item(28000, "Payment", R.drawable.ic_monetization_on, MnType.HEAD);
//        items.add(pym);
//        items.add(new Item(28001, pym.Text, "Card Collections", MnType.SUB, PaymentCardCollections.class));
//        items.add(new Item(28002, pym.Text, "Card Details", MnType.SUB, PaymentCardDetails.class));
//        items.add(new Item(28003, pym.Text, "Form", MnType.SUB, PaymentForm.class));
//        items.add(new Item(28004, pym.Text, "Profile", MnType.SUB, PaymentProfile.class));
//        items.add(new Item(28005, pym.Text, "Success Dialog", MnType.SUB, PaymentSuccessDialog.class));
//
//        Item dsh = new Item(29000, "Dashboard", R.drawable.ic_event_seat, MnType.HEAD);
//        items.add(dsh);
//        items.add(new Item(29001, dsh.Text, "Grid Fab", MnType.SUB, DashboardGridFab.class));
//        items.add(new Item(29002, dsh.Text, "Statistics", MnType.SUB, DashboardStatistics.class));
//        items.add(new Item(29003, dsh.Text, "Pay Bill", MnType.SUB, DashboardPayBill.class));
//        items.add(new Item(29004, dsh.Text, "Flight", MnType.SUB, DashboardFlight.class));
//        items.add(new Item(29005, dsh.Text, "Wallet", MnType.SUB, DashboardWallet.class));
//        items.add(new Item(29006, dsh.Text, "Wallet Green", MnType.SUB, DashboardWalletGreen.class));
//        items.add(new Item(29007, dsh.Text, "Finance", MnType.SUB, DashboardFinance.class));
//        items.add(new Item(29008, dsh.Text, "Cryptocurrency", MnType.SUB, DashboardCryptocurrency.class));
//        items.add(new Item(29009, dsh.Text, "Dana", MnType.SUB, DashboardDana.class));
//        items.add(new Item(29010, dsh.Text, "Travel", MnType.SUB, DashboardTravel.class));
//        items.add(new Item(29011, dsh.Text, "News", MnType.SUB, DashboardNews.class));
//        items.add(new Item(29012, dsh.Text, "Courses Progress", MnType.SUB, DashboardCoursesProgress.class));
//        items.add(new Item(29013, dsh.Text, "Travel Package", MnType.SUB, DashboardTravelPackage.class));
//
//        Item art = new Item(30000, "Article", R.drawable.ic_subject, MnType.HEAD);
//        items.add(art);
//        items.add(new Item(30001, art.Text, "Simple", MnType.SUB, ArticleSimple.class));
//        items.add(new Item(30002, art.Text, "Medium", MnType.SUB, ArticleMedium.class));
//        items.add(new Item(30003, art.Text, "Medium Dark", MnType.SUB, ArticleMediumDark.class));
//        items.add(new Item(30004, art.Text, "Big Header", MnType.SUB, ArticleBigHeader.class));
//        items.add(new Item(30005, art.Text, "Stepper", MnType.SUB, ArticleStepper.class));
//        items.add(new Item(30006, art.Text, "Card", MnType.SUB, ArticleCard.class));
//        items.add(new Item(30007, art.Text, "Food", MnType.SUB, ArticleFood.class));
//        items.add(new Item(30008, art.Text, "Food Review", MnType.SUB, ArticleFoodReview.class));
//        items.add(new Item(30009, art.Text, "Details", MnType.SUB, ArticleDetails.class));
//        items.add(new Item(30010, art.Text, "Book Review", MnType.SUB, ArticleBookReview.class));
//
//        Item ab = new Item(31000, "About", R.drawable.ic_perm_device_info, MnType.HEAD);
//        items.add(ab);
//        items.add(new Item(31001, ab.Text, "App", MnType.SUB, AboutApp.class));
//        items.add(new Item(31002, ab.Text, "App Simple", MnType.SUB, AboutAppSimple.class));
//        items.add(new Item(31003, ab.Text, "App Simple Blue", MnType.SUB, AboutAppSimpleBlue.class));
//        items.add(new Item(31004, ab.Text, "Company", MnType.SUB, AboutCompany.class));
//        items.add(new Item(31005, ab.Text, "Company Image", MnType.SUB, AboutCompanyImage.class));
//        items.add(new Item(31006, ab.Text, "Company Card", MnType.SUB, AboutCompanyCard.class));
//        items.add(new Item(31007, ab.Text, "Dialog Main Action", MnType.SUB, AboutDialogMainAction.class));
//
//        Item cht = new Item(32000, "Chat", R.drawable.ic_chat, MnType.HEAD);
//        items.add(cht);
//        items.add(new Item(32001, cht.Text, "Telegram", MnType.SUB, ChatTelegram.class));
//        items.add(new Item(32002, cht.Text, "WhatsApp", MnType.SUB, ChatWhatsapp.class));
//        items.add(new Item(32003, cht.Text, "Facebook", MnType.SUB, ChatFacebook.class));
//        items.add(new Item(32004, cht.Text, "BBM", MnType.SUB, ChatBBM.class));
//
//        items.add(new Item(-1, "Application", MnType.DIV));
//        items.add(new Item(1, "About", R.drawable.ic_error_outline, MnType.NOR));
//
//        search_items.clear();
//        for (Item i : items) {
//            if (i.ItemType == MnType.SUB.getValue()) search_items.add(i);
//        }
//
//        return items;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        Tools.changeMenuIconColor(menu, Color.WHITE);
        Tools.changeOverflowMenuIconColor(toolbar, Color.WHITE);

        final MenuItem menu_notif = menu.findItem(R.id.action_notifications);
        View actionView = MenuItemCompat.getActionView(menu_notif);
        notif_badge = actionView.findViewById(R.id.notif_badge);
        setupBadge();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menu_notif);
            }
        });

        MenuItem action_search = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(action_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                searchAdapter.getFilter().filter(query);
                return false;
            }
        });

//        MenuItemCompat.setOnActionExpandListener(action_search, new MenuItemCompat.OnActionExpandListener() {
//            @Override
//            public boolean onMenuItemActionCollapse(MenuItem item) {
//                nested_scrollview.setVisibility(View.VISIBLE);
//                search_recycler.setVisibility(View.GONE);
//                menu_notif.setVisible(true);
//                initToolbar();
//                initDrawerMenu();
//                return true;
//            }
//
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem item) {
//                nested_scrollview.setVisibility(View.GONE);
//                search_recycler.setVisibility(View.VISIBLE);
//                actionBar.setDisplayHomeAsUpEnabled(false);
//                actionBar.setHomeButtonEnabled(false);
//                menu_notif.setVisible(false);
//                return true;
//            }
//        });

//        searchView.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//
//                return false;
//            }
//        });

        return true;
    }

    private void setupBadge() {
        if (notif_badge == null) return;
        if (notification_count == 0) {
            notif_badge.setVisibility(View.GONE);
        } else {
            notif_badge.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if (item_id == R.id.action_notifications) {

        }
        return super.onOptionsItemSelected(item);
    }

//    private void initDrawerMenu() {
//        final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
//            public void onDrawerOpened(View drawerView) {
//                updateCounter(nav_view);
//                super.onDrawerOpened(drawerView);
//            }
//        };
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(final MenuItem item) {
//                int item_id = item.getItemId();
//                if (item_id == R.id.action_portfolio) {
//                    Tools.openInAppBrowser(MainMenu.this, "http://portfolio.dream-space.web.id/", false);
//                } else if (item_id == R.id.action_notifications) {
//
//                } else if (item_id == R.id.action_rate) {
//                    Tools.rateAction(MainMenu.this);
//                } else if (item_id == R.id.action_about) {
//                    showDialogAbout();
//                }
//                return true;
//            }
//        });
//
//        // navigation header
//        navigation_header = nav_view.getHeaderView(0);
//
//        TextView tv_new_version = (TextView) navigation_header.findViewById(R.id.tv_new_version);
//        ImageButton bt_update = (ImageButton) navigation_header.findViewById(R.id.bt_update);
//        tv_new_version.setVisibility(View.GONE);
//        bt_update.setVisibility(View.GONE);
//        bt_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Tools.rateAction(MainMenu.this);
//            }
//        });
//    }

    private void updateCounter(NavigationView nav) {
        Menu m = nav.getMenu();
        View drw_notif_badge = (m.findItem(R.id.action_notifications).getActionView().findViewById(R.id.notif_badge));
        if (notification_count == 0) {
            drw_notif_badge.setVisibility(View.GONE);
        } else {
            drw_notif_badge.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        doExitApp();
    }

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "Press again to exit app", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

//    private void showDialogAbout() {
//        final Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
//        dialog.setContentView(R.layout.dialog_about);
//        dialog.setCancelable(true);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        ((TextView) dialog.findViewById(R.id.tv_version)).setText("Version " + BuildConfig.VERSION_NAME);
//
//        ((View) dialog.findViewById(R.id.bt_getcode)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("https://codecanyon.net/user/dream_space/portfolio"));
//                startActivity(i);
//            }
//        });
//
//        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        ((Button) dialog.findViewById(R.id.bt_rate)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Tools.rateAction(MainMenu.this);
//            }
//        });
//
//        ((Button) dialog.findViewById(R.id.bt_portfolio)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Tools.openInAppBrowser(MainMenu.this, "http://portfolio.dream-space.web.id/", false);
//            }
//        });
//
//        sharedPref.setFirstLaunch(false);
//        dialog.show();
//        dialog.getWindow().setAttributes(lp);
//    }

    private void showDialogOffer() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_offer);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ((View) dialog.findViewById(R.id.bt_getcode)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://codecanyon.net/user/dream_space/portfolio"));
                startActivity(i);
            }
        });

        sharedPref.setFirstLaunch(false);
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        active = false;
    }

    private void moPubInitBanner() {

    }

    private void showBanner() {

    }

    private void prepareInterstitial() {

    }

    /* show ads */
    public boolean showInterstitial() {
        return true;
    }


}
