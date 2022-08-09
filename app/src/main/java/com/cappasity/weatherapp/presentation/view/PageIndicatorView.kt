package com.cappasity.weatherapp.presentation.view

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.cappasity.weatherapp.R
import com.cappasity.weatherapp.domain.extension.dpToPx
import com.cappasity.weatherapp.domain.extension.getColor

class PageIndicatorView : LinearLayout {

    /**
     * Тип индикатора.
     * FILL - Простая замена drawable
     * FILL_WITH_ANIMATION - замена drawable с анимацией.
     */
    annotation class IndicatorType {
        companion object {
            const val FILL = 0
            const val FILL_WITH_ANIMATION = 1
        }
    }

    /**
     * Enum класс для определения состояния индикатора
     * (пустой, закрашенный (введена цифра пароля) или неверный пароль)
     */
    private enum class AnimationState {
        EMPTY,
        FILL,
    }

    companion object {
        var DEFAULT_DOTS_COUNT = 6
        private const val DEFAULT_ANIMATION_DURATION = 200
    }

    // region Properties

    private val animatorSet: Array<ValueAnimator>

    /** Время анимации. */
    private val mAnimationDuration: Int

    /** Диаметр индикатора. */
    private val mDotDiameter: Int

    /** Пространство до индикатора и после. */
    private val mDotSpacing: Int

    /** Background для индикатора (drawable). */
    private val mBackground: Int

    /** Цвет пустого индикатора. */
    private val mEmptyColor: Int

    /** Цвет заполненого индикатора. */
    private val mFilledColor: Int

    /** Длина . */
    var mDotsCount: Int

    /** Состояния анимации индикаторов на предыдущей итерации. */
    private val mPrevAnimationDotsStates: Array<AnimationState>

    /** Тип индикатора [IndicatorType]. */
    private var mIndicatorType: Int = 0

    // endregion

    // region Init

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.PageIndicatorView,
            0, 0
        )

        // Получение параметров из разметки.
        try {
            mDotDiameter = typedArray.getDimension(
                R.styleable.PageIndicatorView_dotDiameter,
                dpToPx(R.dimen.indicatorDotsDiameter)
            ).toInt()
            mDotSpacing = typedArray.getDimension(
                R.styleable.PageIndicatorView_dotSpacing,
                dpToPx(R.dimen.indicatorDotsSpacing)
            ).toInt()

            mBackground = R.drawable.bg_indicator

            mEmptyColor = typedArray.getColor(
                R.styleable.PageIndicatorView_dotEmptyColor,
                getColor(R.color.color_indicator_empty)
            )
            mFilledColor = typedArray.getColor(
                R.styleable.PageIndicatorView_dotFilledColor,
                getColor(R.color.color_indicator_selected)
            )

            mAnimationDuration = typedArray.getInt(
                R.styleable.PageIndicatorView_dotAnimationDuration,
                DEFAULT_ANIMATION_DURATION
            )

            mDotsCount = typedArray.getInt(
                R.styleable.PageIndicatorView_dotsCount,
                DEFAULT_DOTS_COUNT
            )
            mIndicatorType = typedArray.getInt(
                R.styleable.PageIndicatorView_dotsIndicatorType,
                IndicatorType.FILL
            )
        } finally {
            typedArray.recycle()
        }

        animatorSet = Array(mDotsCount) {
            ValueAnimator.ofObject(ArgbEvaluator(), mEmptyColor, mFilledColor)
        }

        // Инициализация массива состояний анимации.
        mPrevAnimationDotsStates = Array(mDotsCount) { AnimationState.EMPTY }

        // Инициализация индикаторов.
        initView()
    }

    /**
     * Инициализация индикаторов.
     */
    private fun initView() {
        ViewCompat.setLayoutDirection(this, ViewCompat.LAYOUT_DIRECTION_LTR)
        for (i in 0 until mDotsCount) {
            // Создание view для индикатора.
            val dot = View(context)
            // Установка view background'a и его цвета.
            setEmptyDot(dot)

            // Настройка параметров индикатора.
            val params = LayoutParams(
                mDotDiameter,
                mDotDiameter
            )
            params.setMargins(mDotSpacing, 0, mDotSpacing, 0)
            dot.layoutParams = params

            // Добавление индикатора на layout.
            addView(dot)
        }
    }

    // endregion

    // region Exposed API and functionality

    /**
     * Функция устанавливает один активированный индикатор.
     */
    fun updatePosition(position: Int) {
        for (i in 0 until mDotsCount) {
            if (i == position) {
                fillDot(i)
            } else {
                emptyDot(i)
            }
        }
    }

    /**
     * Метод для настройки индикатора. Установка background'a и его цвета.
     */
    private fun setEmptyDot(dot: View) {
        dot.background = ContextCompat.getDrawable(context, mBackground)
        dot.setBackgroundFilter(mEmptyColor)
    }

    /**
     * Метод для установки пустого цвета индикатора.
     */
    private fun emptyDot(dotPos: Int) {
        val dot = getChildAt(dotPos)
        // Если тип простого заполнения, то просто меняем цвет background'a
        if (mIndicatorType == IndicatorType.FILL) {
            dot.setBackgroundFilter(mEmptyColor)
        } // Если тип с анимацией, то в зависимости от предыдущего состояния индикатора.
        else if (mIndicatorType == IndicatorType.FILL_WITH_ANIMATION) {
            when (mPrevAnimationDotsStates[dotPos]) {
                AnimationState.EMPTY -> {

                }

                AnimationState.FILL -> {
                    startAnimation(
                        mFilledColor,
                        mEmptyColor, dot
                    )
                }
            }
            mPrevAnimationDotsStates[dotPos] = AnimationState.EMPTY
        }
    }

    /**
     * Метод для установки активного цвета индикатора.
     */
    private fun fillDot(dotPos: Int) {
        val dot = getChildAt(dotPos)
        if (mIndicatorType == IndicatorType.FILL) {
            dot.setBackgroundFilter(mFilledColor)
        } else if (mIndicatorType == IndicatorType.FILL_WITH_ANIMATION) {
            when (mPrevAnimationDotsStates[dotPos]) {
                AnimationState.EMPTY -> {
                    startAnimation(
                        mEmptyColor,
                        mFilledColor, dot
                    )
                }

                AnimationState.FILL -> {
                }
            }
            mPrevAnimationDotsStates[dotPos] = AnimationState.FILL
        }
    }

    /**
     * Метод для применения анимации к индикатору.
     */
    private fun startAnimation(colorFrom: Int, colorTo: Int, dot: View) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = mAnimationDuration.toLong()
        colorAnimation.addUpdateListener {
            dot.setBackgroundFilter((colorAnimation.animatedValue as Int))
        }
        colorAnimation.start()
    }

    @Suppress("DEPRECATION")
    private fun View.setBackgroundFilter(color: Int) {
        this.background.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }

    // endregion

}