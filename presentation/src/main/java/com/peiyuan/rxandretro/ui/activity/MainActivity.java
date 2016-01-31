package com.peiyuan.rxandretro.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.peiyuan.common.util.ViewServer;
import com.peiyuan.model.api.NetApi;
import com.peiyuan.model.db.greendao.DaoMaster;
import com.peiyuan.model.db.greendao.DaoSession;
import com.peiyuan.model.db.greendao.Note;
import com.peiyuan.model.db.greendao.NoteDao;
import com.peiyuan.model.db.table.ArticleTable;
import com.peiyuan.model.entity.ArticleListEntity;
import com.peiyuan.rxandretro.R;
import com.peiyuan.rxandretro.component.ApplicationComponent;
import com.peiyuan.rxandretro.component.DaggerActivityComponent;
import com.peiyuan.rxandretro.module.ActivityModule;
import com.peiyuan.rxandretro.ui.base.BaseActivity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends BaseActivity {


    @Inject
    NetApi netApiService;

    @Inject
    Realm realm;

    @Bind(R.id.fabBtn)
    FloatingActionButton fabBtn;
    @Bind(R.id.rootLayout)
    CoordinatorLayout rootLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ViewServer.get(this).addWindow(this);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "GreenDB", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        DaoSession session = daoMaster.newSession();
        final NoteDao noteDao = session.getNoteDao();

        Observable.create(new Observable.OnSubscribe<NoteDao>() {
            @Override
            public void call(Subscriber<? super NoteDao> subscriber) {
                Note note = new Note();
                note.setId((long) 10086);
                note.setComment("hahaha");
                note.setDate(new Date());
                note.setText("wawawa");
                noteDao.insertOrReplace(note);
                subscriber.onNext(noteDao);
            }

        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<NoteDao>() {
            @Override
            public void call(NoteDao noteDao) {
                Timber.d("给力");
            }
        });


        initListener();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


//        RxView.clicks(fab).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                Call<ResponseBody> call = netApiService.getArticleDetail(191);
//                call.enqueue(new Callback<ResponseBody>() {
//
//                    @Override
//                    public void onResponse(retrofit2.Response<ResponseBody> response) {
//                        try {
//                            Toast.makeText(MainActivity.this,response.body().string().toString(),Toast.LENGTH_SHORT).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Throwable t) {
//                        Toast.makeText(MainActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//
//        netApiService.getHomeArticle(0,0,10,0).subscribe(new Action1<JsonObject>() {
//            @Override
//            public void call(JsonObject s) {
//                Toast.makeText(MainActivity.this,s.toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        RxView.clicks(fabBtn).throttleFirst(1500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Snackbar.make(rootLayout, "有何吩咐?", Snackbar.LENGTH_SHORT).setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Call<ArticleListEntity> call = netApiService.getArticleDetail(191);
                        call.enqueue(new Callback<ArticleListEntity>() {

                            @Override
                            public void onResponse(final retrofit2.Response<ArticleListEntity> response) {
                                try {
                                    Toast.makeText(MainActivity.this,response.body().getArticles().get(0).getContent(),Toast.LENGTH_SHORT).show();
                                    realm.beginTransaction();
                                    ArticleTable articleTable = new ArticleTable();
                                    articleTable.set_id(10010);
                                    articleTable.setCommentCount(100);
                                    articleTable.setBrief("国安是冠军");
                                    realm.copyToRealmOrUpdate(articleTable);
                                    realm.commitTransaction();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Toast.makeText(MainActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).show();
            }
        });
    }

    @Override
    protected void setupActivityComponent(ApplicationComponent applicationComponent) {
        DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(this)).build().inject(this);

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        realm.close();
        ViewServer.get(this).removeWindow(this);
        super.onDestroy();
    }
}
