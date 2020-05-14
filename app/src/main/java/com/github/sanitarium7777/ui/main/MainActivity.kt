package com.github.sanitarium7777.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.github.sanitarium7777.R
import com.github.sanitarium7777.data.model.MenuResponse
import com.github.sanitarium7777.ui.image.ImageFragment
import com.github.sanitarium7777.ui.message.MessageFragment
import com.github.sanitarium7777.ui.url.UrlFragment
import com.github.sanitarium7777.utils.Status
import com.github.sanitarium7777.utils.gone
import com.github.sanitarium7777.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerToggle = setupDrawerToggle()
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()
        drawer_layout.addDrawerListener(drawerToggle)

        nav_view.setNavigationItemSelectedListener { item ->
            viewModel.getMenuItemByPosition(item.itemId)?.let {
                loadFragmentForMenuItem(it)
            }
            checkItemById(item.itemId)
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    private fun checkItemById(id: Int) {
        nav_view.menu.setGroupCheckable(DEFAULT_SUB_MENU_ID, true, true)
        nav_view.menu.getItem(id).isChecked = true
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        return ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.drawer_open,
            R.string.drawer_close
        )
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    private fun setupObservers() {
        viewModel.getMenu().apply {
            if (value?.status != Status.LOADING) {
                observe(this@MainActivity, Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                progressBar.gone()
                                resource.data?.let { menu -> retrieveList(menu) }
                            }
                            Status.ERROR -> {
                                progressBar.gone()
                                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG)
                                    .show()
                            }
                            Status.LOADING -> {
                                progressBar.visible()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun retrieveList(menuJson: MenuResponse) {
        val menu = nav_view.menu
        //val submenu: Menu = menu.addSubMenu(R.string.sub_menu_title)
        menuJson.menu.forEach {
            //submenu.add(it.name)
            menu.add(DEFAULT_SUB_MENU_ID, menuJson.menu.indexOf(it), 0, it.name)
        }

        viewModel.getCurrentMenuItem()?.let {
            loadFragmentForMenuItem(it)
            checkItemById(viewModel.currentItemPosition)
        }
        nav_view.invalidate()
    }

    private fun loadFragmentForMenuItem(menuItem: MenuResponse.Menu) {
        val fragment = when (menuItem.function) {
            MenuResponse.MenuFunction.IMAGE -> {
                ImageFragment.newInstance(menuItem.param)
            }
            MenuResponse.MenuFunction.TEXT -> {
                MessageFragment.newInstance(menuItem.param)
            }
            MenuResponse.MenuFunction.URL -> {
                UrlFragment.newInstance(menuItem.param)
            }
        }
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.nav_host_fragment, fragment)
        ft.commit()
        supportActionBar?.title = menuItem.name
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {
        private const val DEFAULT_SUB_MENU_ID = 0
    }
}
