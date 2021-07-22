package com.exemplo.RickAndMorty.ui.fragment.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.exemplo.RickAndMorty.R
import com.exemplo.RickAndMorty.api.Repository
import com.exemplo.RickAndMorty.extensions.getTextButtonChecked
import com.exemplo.RickAndMorty.extensions.getTextChipChecked
import com.exemplo.RickAndMorty.extensions.setButtonChecked
import com.exemplo.RickAndMorty.extensions.setChipChecked
import com.exemplo.RickAndMorty.ui.fragment.SharedViewModel
import com.exemplo.RickAndMorty.ui.fragment.SharedViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_filter.*


class FilterFragment : BottomSheetDialogFragment() {
    private val viewModel: SharedViewModel by activityViewModels{SharedViewModelFactory(Repository())}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.filterValue.observe(viewLifecycleOwner, {
            chipgroup_status.setChipChecked(it[0])
            radiogroup_gender.setButtonChecked(it[1])
        })

        btn_make_filter.setOnClickListener {
            if(chipgroup_status.getTextChipChecked().isNotEmpty() && radiogroup_gender.getTextButtonChecked().isNotEmpty()) {
                viewModel.getByStatusAndGender(chipgroup_status.getTextChipChecked(), radiogroup_gender.getTextButtonChecked(), 1)
            }else{
                if(chipgroup_status.getTextChipChecked().isNotEmpty()){
                    viewModel.getByStatus(chipgroup_status.getTextChipChecked(), 1)
                }else{
                    viewModel.getByGender(radiogroup_gender.getTextButtonChecked(), 1)
                }
            }

            viewModel.filterValue.value = arrayOf(chipgroup_status.checkedChipId, radiogroup_gender.checkedRadioButtonId)

            findNavController().popBackStack(R.id.listFragment, false)
        }
    }
}